package com.rainng.coursesystem.service.student;

import com.rainng.coursesystem.dao.mapper.TeacherMapper;
import com.rainng.coursesystem.dao.mapper.TeacherStudentRelMapper;
import com.rainng.coursesystem.manager.OptionManager;
import com.rainng.coursesystem.manager.admin.TeacherManager;
import com.rainng.coursesystem.manager.student.CourseSelectManager;
import com.rainng.coursesystem.model.bo.StudentCourseSelectItemBO;
import com.rainng.coursesystem.model.entity.*;
import com.rainng.coursesystem.model.enums.*;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.table.StudentCourseSelectItemVO;
import com.rainng.coursesystem.model.vo.response.table.TeacherDao;
import com.rainng.coursesystem.model.vo.response.table.TeacherItemVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.util.LessonTimeConverter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CourseSelectService extends BaseService {
    private final CourseSelectManager manager;
    private final TeacherManager teacherManager;
    private final OptionManager optionManager;
    private final LessonTimeConverter lessonTimeConverter;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherStudentRelMapper teacherStudentRelMapper;


    public CourseSelectService(CourseSelectManager manager, OptionManager optionManager, LessonTimeConverter lessonTimeConverter,TeacherManager teacherManager) {
        this.manager = manager;
        this.optionManager = optionManager;
        this.lessonTimeConverter = lessonTimeConverter;
        this.teacherManager = teacherManager;
    }

    public ResultVO getPageCount(String courseName, String teacherName) {
        //Integer studentId = getUserId();
        return result(teacherManager.getPageCount( courseName, teacherName));
    }

    public ResultVO getPage(Integer index, String courseName, String teacherName) {
//        Integer studentId = getUserId();

//        List<StudentCourseSelectItemBO> boList = manager.getPage(index, studentId, courseName, teacherName);
//        List<StudentCourseSelectItemVO> voList = new ArrayList<>(boList.size());
//
//        for (StudentCourseSelectItemBO bo : boList) {
//            StudentCourseSelectItemVO vo = new StudentCourseSelectItemVO();
//            BeanUtils.copyProperties(bo, vo);
//            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
//            voList.add(vo);
//        }

         List<TeacherEntity> list =  teacherManager.getPageEntity(index, courseName, teacherName);
        List<TeacherDao> teacherDaos = new ArrayList<>();

        list.forEach(value -> {
            TeacherDao teacherDao = new TeacherDao();
            BeanUtils.copyProperties(value, teacherDao);
            teacherDao.setSex(SexEnum.getNameByCode(value.getSex()));
            teacherDao.setTitle(TitleTypeEnum.getNameByCode(value.getTitle()));
            teacherDao.setTeacherType(TeacherTypeEnum.getNameByCode(value.getTeacherType()));
            teacherDaos.add(teacherDao);
        });

        return result(teacherDaos);
    }

    public ResultVO create(Integer courseId) {
        Integer studentId = getUserId();

        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("现在不是选课时间!");
        }
        StudentEntity student = manager.getStudentById(studentId);
        CourseEntity course = manager.getCourseById(courseId);
        if (student == null) {
            return failedResult("学生Id:" + studentId + "不存在!");
        }
        if (course == null) {
            return failedResult("课程Id:" + courseId + "不存在!");
        }
        if (!manager.inSameDepartment(courseId, studentId)) {
            return failedResult("学生不能选择非教学系的课程!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("课容量已满!");
        }
        if (manager.getStudentCourseByCourseIdAndStudentId(courseId, studentId) != null) {
            return failedResult("学生已选修此课程!");
        }
        if (!manager.getStudentGradeById(student.getId()).equals(course.getGrade())) {
            return failedResult("学生与课程不在同一年级");
        }
        String timePart = splitTimePart(course.getTime());
        if (manager.countStudentCourseSelectedByTimePart(studentId, timePart) > 0) {
            return failedResult("上课时间冲突!");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        manager.create(studentCourse);

        return result("选课成功");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }

    //选课
    @Transactional
    public ResultVO chooseTeacher( String teacherUuid , Integer scienceType) {
        TeacherEntity teacherEntity =  teacherMapper.selectByUuid(teacherUuid);
        if (Objects.isNull(teacherEntity)){
            return failedResult("当前老师不存在");
        }
        if (Strings.isEmpty(ScienceEnum.getNameByCode(scienceType))){
            return failedResult("学术类型选取失败，请刷新页面重试！");
        }
        if (scienceType == ScienceEnum.SCIENCE.getCode() && teacherEntity.getScienceSurplus() > 0){
            teacherEntity.setScienceSurplus(teacherEntity.getScienceSurplus() - 1);
        }else if (scienceType == ScienceEnum.NO_SCIENCE.getCode() && teacherEntity.getNoScienceSurplus() > 0){
            teacherEntity.setNoScienceSurplus(teacherEntity.getNoScienceSurplus() - 1);
        }else {
            return  failedResult(String.format("选课失败，当前导师下的%s名额已满",ScienceEnum.getNameByCode(scienceType)));
        }
        StudentEntity studentEntity = manager.getStudentById(getUserId());
        if (Objects.isNull(studentEntity)){
            return  failedResult("当前登录状态丢失");
        }

        TeacherStudentRel teacherStudentRelOld = teacherStudentRelMapper.selectAppleRecode(teacherUuid,studentEntity.getStudentUuid());
        if (!Objects.isNull(teacherStudentRelOld)){
            return  failedResult(String.format("您已经申请了%s导师的%s,请勿重复选择",teacherEntity.getName(), ScienceEnum.getNameByCode(scienceType)));
        }

        TeacherStudentRel teacherStudentRel = new TeacherStudentRel();
        teacherStudentRel.setId(UUID.randomUUID().toString());
        teacherStudentRel.setIsScience(scienceType);
        teacherStudentRel.setStudentUuid(studentEntity.getStudentUuid());
        teacherStudentRel.setTeacherUuid(teacherUuid);
        teacherStudentRel.setStatus(ApplyStatusEnum.PROCESS.getCode());

        teacherMapper.updateById(teacherEntity);
        teacherStudentRelMapper.insert(teacherStudentRel);
        return result(null,"选课成功");
    }
}
