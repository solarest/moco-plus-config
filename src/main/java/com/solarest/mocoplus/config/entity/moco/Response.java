package com.solarest.mocoplus.config.entity.moco;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author jinjian
 */
@Data
public class Response {

    enum ResponseType {

        /**
         * text type
         */
        TEXT,

        /**
         * JSON type
         */
        JSON
    }

    private ResponseType responseType;

    private Object response;

    public Response() {
    }

    public Response(ResponseType responseType, String response) {
        this.responseType = responseType;
        this.response = response;
        check();
    }

    private void check() {
        if (this.responseType == ResponseType.JSON) {
            JSONObject.parse(String.valueOf(response));
        }
    }

    public JSONObject toConfig() {

        JSONObject json = new JSONObject();
        if (responseType == ResponseType.JSON) {
            json.put("json", JSONObject.parse(String.valueOf(response)));
        }

        if (responseType == ResponseType.TEXT) {
            json.put("text", response.toString());
        }
        return json;
    }

    public Response toBean(String text) {
        JSONObject json = JSON.parseObject(text);
        return this.toBean(json);
    }

    public Response toBean(JSONObject json) {
        Response response = new Response();

        if (json.containsKey(ResponseType.JSON.name().toLowerCase())) {
            response.setResponseType(ResponseType.JSON);
            response.setResponse(json.get(ResponseType.JSON.name().toLowerCase()));
        }

        if (json.containsKey(ResponseType.TEXT.name().toLowerCase())) {
            response.setResponseType(ResponseType.TEXT);
            response.setResponse(json.get(ResponseType.TEXT.name().toLowerCase()));
        }
        return response;
    }

    /**
     * json\text format convertor
     *
     * @param oriResponse original response entity input
     * @return converted response entity
     */
    public static Response convert(Response oriResponse) {
        String content = (String) oriResponse.getResponse();

        if (oriResponse.responseType == ResponseType.JSON) {
            content = content.replace("\"", "\\\"");
            oriResponse.setResponse(content);
        }

        if (oriResponse.responseType == ResponseType.TEXT) {
            content = content.replace("\\\"", "\"");
            // if not be satisfied to json format should throw exception
            oriResponse.setResponse(JSONObject.parseObject(content));
        }
        return oriResponse;
    }
}
