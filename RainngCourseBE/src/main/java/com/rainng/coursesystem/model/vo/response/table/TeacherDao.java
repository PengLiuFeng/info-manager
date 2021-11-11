package com.rainng.coursesystem.model.vo.response.table;

import lombok.Data;

@Data
public class TeacherDao {

    private String sex;

    private String title;

    private String teacherType;

    private Integer scienceInTrue;

    private Integer scienceOutTrue;

    private Integer scienceSum;

    private Integer scienceSurplus;

    private Integer noScienceInTrue;

    private Integer noScienceOutTrue;

    private Integer noScienceSum;

    private Integer noScienceSurplus;

    private String teacherUuid;

    private Integer id;

    private Integer departmentId;

    private String number;

    private String name;

}
