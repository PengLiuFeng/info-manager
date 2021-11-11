package com.rainng.coursesystem.model.enums;

public enum TeacherTypeEnum {
    BD(1,"博导"),
    SD(2,"硕导");

    private Integer code ;
    private String name;

    TeacherTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code){
        for (TeacherTypeEnum value : TeacherTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name){
        for (TeacherTypeEnum value : TeacherTypeEnum.values()) {
            if (value.getName().equals(name)){
                return value.getCode();
            }
        }
        return null;
    }
}
