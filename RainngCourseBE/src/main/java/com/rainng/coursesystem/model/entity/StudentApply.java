package com.rainng.coursesystem.model.entity;

import lombok.Data;

@Data
public class StudentApply extends StudentEntity {
    private Integer isScience;
    private int status ;

    private String science;

    private String applyId;
}
