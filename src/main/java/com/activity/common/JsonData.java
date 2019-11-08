package com.activity.common;

import lombok.Data;

@Data
public class JsonData {

    private String code;
    private String msg;
    private Object object;

    public JsonData() {
    }

    public JsonData(Object object) {
        this.code = ErrorEnum.S200.getCode();
        this.msg = ErrorEnum.S200.getDesc();
        this.object = object;
    }

    public JsonData(String code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }
}
