package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainng.coursesystem.model.entity.StudentApply;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.vo.response.StudentInfoVO;
import com.rainng.coursesystem.model.vo.response.table.StudentItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends CSBaseMapper<StudentEntity> {
    Integer getDepartmentIdById(Integer studentId);

    Integer getGradeById(Integer studentId);

    Integer count(String majorName, String className, String name);

    IPage<StudentItemVO> getPage(IPage<StudentItemVO> page, String majorName, String className, String name);

    StudentInfoVO getStudentInfoById(Integer studentId);

    List<StudentApply> getStudentByTeacherUUID(String teacherId);

    List<StudentEntity> getStudentByUUID(String studentUuid);
}
