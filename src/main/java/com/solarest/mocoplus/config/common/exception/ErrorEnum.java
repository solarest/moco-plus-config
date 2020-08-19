package com.solarest.mocoplus.config.common.exception;

/**
 * @author solar
 */
public enum ErrorEnum {

    /**
     * data format error
     */
    DATA_FORMAT_EXCEPTION("400001", "data format exception"),

    /**
     * unknown exception
     */
    SYSTEM_EXCEPTION("400999", "unknown exception");

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
