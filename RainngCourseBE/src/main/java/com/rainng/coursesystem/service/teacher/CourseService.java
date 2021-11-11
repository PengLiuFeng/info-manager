package com.rainng.coursesystem.service.teacher;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.coursesystem.dao.mapper.TeacherMapper;
import com.rainng.coursesystem.dao.mapper.TeacherStudentRelMapper;
import com.rainng.coursesystem.manager.teacher.CourseManager;
import com.rainng.coursesystem.model.entity.StudentApply;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.entity.TeacherStudentRel;
import com.rainng.coursesystem.model.enums.ApplyStatusEnum;
import com.rainng.coursesystem.model.enums.ScienceEnum;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.table.TeacherCourseItemVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.util.LessonTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service("teacher_courseService")
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherStudentRelMapper teacherStudentRelMapper;


    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO list() {
        Integer teacherId = getUserId();

        TeacherEntity teacherEntity = teacherMapper.selectEntityByID(teacherId);

        List<StudentApply> list = manager.getStudentByTeacherUUID(teacherEntity.getTeacherUuid());
        list.stream().forEach(value -> {
            value.setScience(ScienceEnum.getNameByCode(value.getIsScience()));
        });
        return result(list);
    }

    @Transactional
    public ResultVO deployApple(String id, Integer status) {
        TeacherStudentRel teacherStudentRel = teacherStudentRelMapper.selectById(id);
        if (Objects.isNull(teacherStudentRel)){
            return failedResult("当前申请已经被撤销，请刷新页面重试！");
        }
        if (teacherStudentRel.getStatus() != ApplyStatusEnum.PROCESS.getCode()){
            return failedResult("当前的申请已经被处理，请刷新页面重试");
        }
        teacherStudentRel.setStatus(status);
        if (ApplyStatusEnum.REJECT.getCode().equals(status)){
            TeacherEntity teacherEntity = teacherMapper.selectByUuid(teacherStudentRel.getTeacherUuid());
            if (ScienceEnum.NO_SCIENCE.getCode().equals(teacherStudentRel.getIsScience())){
                teacherEntity.setNoScienceSurplus(teacherEntity.getNoScienceSurplus() + 1);
            }
            if (ScienceEnum.SCIENCE.getCode().equals(teacherStudentRel.getIsScience())){
                teacherEntity.setScienceSurplus(teacherEntity.getScienceSurplus() + 1);
            }
            teacherMapper.updateById(teacherEntity);
        }
        teacherStudentRelMapper.updateById(teacherStudentRel);
        return result(null);
    }
}
