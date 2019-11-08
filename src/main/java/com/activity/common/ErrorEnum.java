package com.activity.common;

/**
 * 公共错误码枚举类
 */
public enum ErrorEnum {

    S200("200", "成功"),
    E404("404", "请求不存在"),
    E410("410", "参数非法"),
    E501("501","失败"),
    E500("500", "系统错误");


    /**
     * 错误代码
     */
    private String code;

    /**
     * 错误描述
     */
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

