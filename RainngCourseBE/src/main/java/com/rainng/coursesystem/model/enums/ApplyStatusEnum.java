package com.rainng.coursesystem.model.enums;

public enum ApplyStatusEnum {

    PROCESS(0,"申请中"),
    PASS(1,"通过"),
    REJECT(2,"拒绝");

    private Integer code ;
    private String name;

    ApplyStatusEnum(Integer code, String name) {
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
        for (ApplyStatusEnum value : ApplyStatusEnum.values()) {
            if (value.getCode().equals(code)){
                return value.getName();
            }
        }
        return "";
    }
}
