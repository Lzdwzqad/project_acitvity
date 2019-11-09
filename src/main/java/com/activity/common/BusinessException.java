package com.activity.common;

/**
 * 自定义异常
 */
public class BusinessException extends RuntimeException {

    protected String code;

    protected String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException() {
    }

    public BusinessException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getDesc();
    }
}
