package com.rainng.coursesystem.model.entity.mongo;

import lombok.Data;

import java.util.Date;

@Data
public class LogEntity {
    private Integer userId;
    private Integer userType;
    private String requestUrl;
    private String businessTarget;
    private String message;
    private Object exception;
    private Integer resultCode;
    private Long executeTime;
    private Date datetime = new Date();
}
