package com.rainng.coursesystem.model.enums;

public enum ScienceEnum {

    NO_SCIENCE(0,"专硕"),
    SCIENCE(1,"学术");

    private Integer code ;
    private String name;

    ScienceEnum(Integer code, String name) {
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
        for (ScienceEnum value : ScienceEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return "";
    }
}
