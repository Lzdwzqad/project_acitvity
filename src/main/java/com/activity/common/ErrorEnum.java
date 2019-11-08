package com.activity.common;

public enum ErrorEnum {

    S200("200", "成功"),
    E404("404", "请求不存在"),
    E410("410", "参数非法"),
    E500("500", "系统错误");


    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ErrorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

