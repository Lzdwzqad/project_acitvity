package com.activity.common;

import lombok.Data;

/**
 * 响应对象
 */
@Data
public class JsonData {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 响应信息
     */
    private Object data;

    public JsonData() {
    }

    /**
     * 成功响应
     *
     * @param object
     */
    public JsonData(Object object) {
        this.code = ErrorEnum.S200.getCode();
        this.msg = ErrorEnum.S200.getDesc();
        this.data = object;
    }

    /**
     * 自定义响应
     *
     * @param code
     * @param msg
     * @param object
     */
    public JsonData(String code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.data = object;
    }
}
