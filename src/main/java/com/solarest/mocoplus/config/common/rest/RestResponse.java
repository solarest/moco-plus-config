package com.solarest.mocoplus.config.common.rest;

import com.solarest.mocoplus.config.common.exception.ErrorCode;
import lombok.Data;

import java.text.MessageFormat;

/**
 * @author solar
 */
@Data
public class RestResponse<T> {

    public static final String SUCCESS_CODE = "200";

    public static final String SUCCESS_MESSAGE = "success";

    private String code;

    private String message;

    private T content;

    public RestResponse(String code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public static <T> RestResponse<T> ok(T content) {
        return new RestResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, content);
    }

    public static <T> RestResponse<T> error(ErrorCode errorCode, Object... param) {
        String message = MessageFormat.format(errorCode.getErrorMsg(), param);
        return new RestResponse<>(errorCode.getCode(), message, null);
    }
}
