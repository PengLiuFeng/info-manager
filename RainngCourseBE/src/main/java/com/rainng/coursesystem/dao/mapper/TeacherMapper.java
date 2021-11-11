package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.vo.response.table.TeacherCourseItemVO;
import com.rainng.coursesystem.model.vo.response.table.TeacherItemVO;
import com.rainng.coursesystem.model.vo.response.table.TimetableItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends CSBaseMapper<TeacherEntity> {
    Integer count(String departmentName, String name);

    IPage<TeacherItemVO> getPage(IPage<TeacherItemVO> page, String departmentName, String name);

    IPage<TeacherEntity> getPageEntity(IPage<TeacherEntity> page ,String departmentName, String name);

    TeacherEntity getByNumber(String number);

    List<TimetableItemVO> listTeacherTimetable(Integer teacherId);

    List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId);

    TeacherEntity selectEntityByID(Integer teacherId);

    TeacherEntity selectByUuid(String teacherUuid);
}
