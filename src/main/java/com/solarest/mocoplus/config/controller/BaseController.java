package com.solarest.mocoplus.config.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.exception.CommandLineException;
import com.solarest.mocoplus.config.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author JinJian
 */
@Slf4j
@RestControllerAdvice
public class BaseController {

    private static final String CODE = "response_code";
    private static final String MSG = "message";
    private static final String RESULT = "result";
    private static final String SUCCESS_CODE = "00";
    private static final String FAILED_CODE = "03";
    private static final String SUCCESS_MSG = "success";

    /**
     * exception processor
     *
     * @param e exception
     * @return output
     */
    @ExceptionHandler()
    public String exception(Exception e) {
        JSONObject json = new JSONObject();
        json.put(CODE, FAILED_CODE);
        json.put(MSG, e.getMessage());

        String msg = e.getMessage().trim();

        if (e instanceof HttpMessageNotReadableException) {
            json.put(MSG, "Http format exception: " + msg);
        } else if (e instanceof JSONException) {
            json.put(MSG, "JSON format exception: " + msg);
        } else if (e instanceof CommandLineException) {
            json.put(MSG, "CMD running exception: " + msg);
        } else if (e instanceof SystemException) {
            json.put(MSG, "System running exception: " + msg);
        } else {
            json.put(MSG, e.getMessage());
        }
        log.warn("some thing wrong :", e);
        return json.toString();
    }

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
