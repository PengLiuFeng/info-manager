package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("rc_teacher")
@Data
public class TeacherEntity {
    public static final String ID = "teacher_id";
    public static final String DEPARTMENT_ID = "teacher_department_id";
    public static final String NUMBER = "teacher_number";
    public static final String NAME = "teacher_name";
    public static final String PASSWORD = "teacher_password";

    public static final String SEX = "sex";
    public static final String TITLE = "title";
    public static final String TEACHER_TYPE = "teacher_type";
    public static final String SCIENCE_IN_TRUE = "science_in_true";
    public static final String SCIENCE_OUT_TRUE = "science_out_true";
    public static final String SCIENCE_SUM = "science_sum";
    public static final String SCIENCE_SURPLUS = "science_surplus";
    public static final String NO_SCIENCE_IN_TRUE = "no_science_in_true";
    public static final String NO_SCIENCE_OUT_TRUE = "no_science_out_true";
    public static final String NO_SCIENCE_SUM = "no_science_sum";
    public static final String NO_SCIENCE_SURPLUS = "no_science_surplus";
    public static final String TEACHER_UUID = "teacher_uuid";

    @TableField(SEX)
    private Integer sex;

    @TableField(TITLE)
    private Integer title;

    @TableField(TEACHER_TYPE)
    private Integer teacherType;

    @TableField(SCIENCE_IN_TRUE)
    private Integer scienceInTrue;

    @TableField(SCIENCE_OUT_TRUE)
    private Integer scienceOutTrue;

    @TableField(SCIENCE_SUM)
    private Integer scienceSum;

    @TableField(SCIENCE_SURPLUS)
    private Integer scienceSurplus;

    @TableField(NO_SCIENCE_IN_TRUE)
    private Integer noScienceInTrue;

    @TableField(NO_SCIENCE_OUT_TRUE)
    private Integer noScienceOutTrue;

    @TableField(NO_SCIENCE_SUM)
    private Integer noScienceSum;

    @TableField(NO_SCIENCE_SURPLUS)
    private Integer noScienceSurplus;

    @TableField(TEACHER_UUID)
    private String teacherUuid;

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(DEPARTMENT_ID)
    private Integer departmentId;

    @TableField(NUMBER)
    private String number;

    @NotBlank(message = "教师姓名不能为空")
    @TableField(NAME)
    private String name;

    @NotNull
    @TableField(PASSWORD)
    private String password;
}
