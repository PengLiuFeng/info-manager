package com.rainng.coursesystem.model.enums;

public enum TitleTypeEnum {
    JS(1,"教授"),
    FJS(2,"副教授"),
    J_S(3,"讲师");

    private Integer code ;
    private String name;

    TitleTypeEnum(Integer code, String name) {
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
        for (TitleTypeEnum value : TitleTypeEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return "";
    }

    public static Integer getCodeByName(String name){
        for (TitleTypeEnum value : TitleTypeEnum.values()) {
            if (value.getName().equals(name)){
                return value.getCode();
            }
        }
        return null;
    }
}
