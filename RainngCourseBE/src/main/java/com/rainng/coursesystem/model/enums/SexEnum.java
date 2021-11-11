package com.rainng.coursesystem.model.enums;

public enum SexEnum {
    MEN(0,"男"),
    WOMEN(1,"女");

    private Integer code ;
    private String name;

    SexEnum(Integer code, String name) {
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
        for (SexEnum value : SexEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name){
        for (SexEnum value : SexEnum.values()) {
            if (value.getName().equals(name)){
                return value.getCode();
            }
        }
        return null;
    }
}
