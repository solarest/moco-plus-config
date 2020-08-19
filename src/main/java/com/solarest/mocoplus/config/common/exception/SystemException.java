package com.solarest.mocoplus.config.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JinJian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException {

    private String code;

    private String message;

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SystemException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }
}
