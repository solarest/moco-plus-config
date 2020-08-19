package com.solarest.mocoplus.config.common.exception;

import lombok.Data;

/**
 * @author solar
 */
public enum ErrorEnum {

    DATA_FORMAT_ERROR("400001", "data format error");

    private String code;

    private String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
