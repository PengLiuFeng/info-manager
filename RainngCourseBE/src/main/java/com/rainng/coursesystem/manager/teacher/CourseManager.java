package com.rainng.coursesystem.manager.teacher;

import com.rainng.coursesystem.config.themis.annotation.Student;
import com.rainng.coursesystem.dao.StudentDAO;
import com.rainng.coursesystem.dao.TeacherDAO;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.entity.StudentApply;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.vo.response.table.TeacherCourseItemVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacher_CourseManager")
public class CourseManager extends BaseManager {
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;

    public CourseManager(TeacherDAO teacherDAO , StudentDAO studentDAO) {
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    public List<TeacherCourseItemVO> listTeacherCourse(Integer teacherId) {
        return teacherDAO.listTeacherCourse(teacherId);
    }

    public List<StudentApply> getStudentByTeacherUUID(String teacherId) {
        return studentDAO.getStudentByTeacherUUID(teacherId);
    }
}
