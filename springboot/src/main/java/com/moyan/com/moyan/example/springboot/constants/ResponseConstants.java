package com.moyan.com.moyan.example.springboot.constants;

public enum ResponseConstants {

    SUCCESS(1,"成功"),
    FAIL(0,"失败"),

    ;

    private int code;
    private String desc;

    ResponseConstants(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
