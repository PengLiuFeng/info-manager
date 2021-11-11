package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@TableName("rc_student")
@Data
public class StudentEntity {
    public static final String ID = "student_id";
    public static final String CLASS_ID = "student_class_id";
    public static final String NUMBER = "student_number";
    public static final String NAME = "student_name";
    public static final String PASSWORD = "student_password";
    public static final String EMAIL = "student_email";
    public static final String BIRTHDAY = "student_birthday";
    public static final String SEX = "student_sex";
    public static final String LAST_LOGIN_TIME = "student_last_login_time";

    public static final String  STUDENT_UUID= "student_uuid";
    public static final String  POLITICAL= "political";
    public static final String  ENGLISH= "english";
    public static final String  BUSINESS_ONE= "business_one";
    public static final String  BUSINESS_TWO= "business_two";
    public static final String  FIRST_TRY= "first_try";
    public static final String  GRADUATE_SCHOOL= "graduate_school";
    public static final String  GRADUATE_PROJECT= "graduate_project";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(CLASS_ID)
    private Integer classId;

    @TableField(NUMBER)
    private String number;

    @TableField(NAME)
    private String name;

    @TableField(PASSWORD)
    private String password;

    @Email(message = "邮箱格式不正确")
    @TableField(value = EMAIL, updateStrategy = FieldStrategy.IGNORED)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = BIRTHDAY, updateStrategy = FieldStrategy.IGNORED)
    private Date birthday;

    @Range(min = 0, max = 1)
    @TableField(SEX)
    private Integer sex;

    @TableField(POLITICAL)
    private Integer political;

    @TableField(ENGLISH)
    private Integer english;

    @TableField(BUSINESS_ONE)
    private Integer businessOne;

    @TableField(BUSINESS_TWO)
    private Integer businessTwo;

    @TableField(FIRST_TRY)
    private Integer firstTry;

    @TableField(GRADUATE_SCHOOL)
    private String graduateSchool;

    @TableField(GRADUATE_PROJECT)
    private String graduateProject ;

    @TableField(STUDENT_UUID)
    private String studentUuid;

    @TableField(LAST_LOGIN_TIME)
    private Date lastLoginTime;
}
