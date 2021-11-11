package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.TeacherStudentRel;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentRelMapper extends BaseMapper<TeacherStudentRel> {
    TeacherStudentRel selectAppleRecode(String teacherUuid, String studentUuid);
}
