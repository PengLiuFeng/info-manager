package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("teacher_student_rel")
@Data
public class TeacherStudentRel {
    @NotNull
    @TableId(value = "id")
    private String id;

    @NotNull(message = "导师uuid")
    @TableField("teacher_uuid")
    private String teacherUuid;

    @NotNull(message = "学生uuid")
    @TableField("student_uuid")
    private String studentUuid;

    @NotNull(message = "是否学术")
    @TableField("is_science")
    private Integer isScience;

    @NotNull(message = "状态")
    @TableField("status")
    private Integer status;
}
