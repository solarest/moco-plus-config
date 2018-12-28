package com.solarest.mocoplus.config.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author JinJian
 */
@RestControllerAdvice
public class BaseController {

    private static final String CODE = "response_code";
    private static final String MSG = "message";
    private static final String RESULT = "result";
    private static final String SUCCESS_CODE = "00";
    private static final String FAILED_CODE = "03";
    private static final String SUCCESS_MSG = "success";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    String responseSuccess() {
        JSONObject json = new JSONObject();
        json.put(CODE, SUCCESS_CODE);
        json.put(MSG, SUCCESS_MSG);
        return json.toString();
    }

    String response(Object o) {
        JSONObject json = new JSONObject();
        json.put(CODE, SUCCESS_CODE);
        json.put(MSG, SUCCESS_MSG);
        json.put(RESULT, o);
        return json.toString();
    }
}
