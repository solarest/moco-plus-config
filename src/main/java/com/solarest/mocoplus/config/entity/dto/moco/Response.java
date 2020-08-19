package com.solarest.mocoplus.config.entity.dto.moco;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.common.exception.ErrorEnum;
import com.solarest.mocoplus.config.common.exception.SystemException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jinjian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Response extends BaseElement {

    public static final String STATUS_KEY = "status";

    public static final String RESPONSE_JSON_KEY = "json";

    public static final String RESPONSE_TEXT_KEY = "text";

    enum ResponseFormatType {

        /**
         * text type
         */
        TEXT,

        /**
         * JSON type
         */
        JSON
    }

    private ResponseFormatType responseFormatType;

    private String responseStatus = "200";

    private Object response;

    @Override
    public JSONObject toConfig() {
        JSONObject json = new JSONObject();
        if (responseStatus != null && !responseStatus.isEmpty()) {
            json.put(STATUS_KEY, responseStatus);
        }

        if (responseFormatType == ResponseFormatType.JSON) {
            json.put(RESPONSE_JSON_KEY, JSONObject.parse(String.valueOf(response)));
        }

        if (responseFormatType == ResponseFormatType.TEXT) {
            json.put(RESPONSE_TEXT_KEY, response.toString());
        }
        return json;
    }

    public Response toBean(String text) {
        JSONObject json = JSON.parseObject(text);
        Response response = this.toBean(json);
        return response;
    }

    public Response toBean(JSONObject json) {
        Response response = new Response();
        if (json.containsKey(STATUS_KEY)) {
            response.setResponseStatus(json.getString(STATUS_KEY));
        }

        if (json.containsKey(RESPONSE_JSON_KEY)) {
            response.setResponseFormatType(ResponseFormatType.JSON);
            response.setResponse(json.get(RESPONSE_JSON_KEY));
            try {
                JSONObject.parse(String.valueOf(response));
            } catch (Exception e) {
                throw new SystemException(ErrorEnum.DATA_FORMAT_ERROR);
            }
        }

        if (json.containsKey(RESPONSE_TEXT_KEY)) {
            response.setResponseFormatType(ResponseFormatType.TEXT);
            response.setResponse(json.get(RESPONSE_TEXT_KEY));
        }
        return response;
    }

    /**
     * json\text format converter
     *
     * @param oriResponse original response entity input
     * @return converted response entity
     */
    public static Response convert(Response oriResponse) {
        String content = (String) oriResponse.getResponse();

        if (oriResponse.responseFormatType == ResponseFormatType.JSON) {
            content = content.replace("\"", "\\\"");
            oriResponse.setResponseFormatType(ResponseFormatType.TEXT);
            oriResponse.setResponse(content);
        }

        if (oriResponse.responseFormatType == ResponseFormatType.TEXT) {
            content = content.replace("\\\"", "\"");
            // if not be satisfied to json format should throw exception
            oriResponse.setResponseFormatType(ResponseFormatType.JSON);
            oriResponse.setResponse(JSONObject.parseObject(content));
        }
        return oriResponse;
    }
}
