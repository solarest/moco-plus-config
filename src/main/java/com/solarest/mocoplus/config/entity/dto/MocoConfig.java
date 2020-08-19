package com.solarest.mocoplus.config.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.entity.dto.moco.Headers;
import com.solarest.mocoplus.config.entity.dto.moco.Queries;
import com.solarest.mocoplus.config.entity.dto.moco.Response;
import com.solarest.mocoplus.config.entity.dto.moco.Xpath;
import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import com.solarest.mocoplus.config.enumeration.HttpMethod;
import lombok.Data;
import lombok.NonNull;

/**
 * @author JinJian
 */
@Data
public class MocoConfig {

    public static final String HEADER_KEY = "headers";
    public static final String QUERIES_KEY = "queries";
    public static final String XPATH_KEY = "xpath";

    @NonNull
    private String uri;

    @NonNull
    private HttpMethod method;

    private Headers headers;

    private Xpath xpath;

    @NonNull
    private Queries queries;

    @NonNull
    private Response response;

    private String description;

    public MocoConfig toBean(JSONObject json) {
        this.description = json.getString("description");

        JSONObject requestJson = json.getJSONObject("request");

        this.uri = requestJson.getString("uri");
        this.method = HttpMethod.valueOf(requestJson.getString("method").toUpperCase());

        if (requestJson.containsKey(HEADER_KEY)) {
            this.headers = new Headers();
            requestJson.getJSONObject(HEADER_KEY).forEach((k, v) -> this.headers.put(k, (String) v));
        }

        if (requestJson.containsKey(QUERIES_KEY)) {
            this.queries = new Queries();
            requestJson.getJSONObject(QUERIES_KEY).forEach((k, v) -> this.queries.put(k, (String) v));
        }

        if (requestJson.containsKey(XPATH_KEY)) {
            this.xpath = new Xpath();
            requestJson.getJSONObject(XPATH_KEY).forEach((k, v) -> this.xpath.put(k, (String) v));
        }

        JSONObject responseJson = json.getJSONObject("response");
        this.response = new Response().toBean(responseJson);
        return this;
    }

    public JSONObject toJson(MocoRequestConfig config) {
        this.uri = config.getUri();
        this.description = config.getDescription();
        this.method = config.getMethod();

        this.headers = (Headers) new Headers().toBean(config.getHeaders());
        this.xpath = (Xpath) new Xpath().toBean(config.getXpath());
        this.queries = (Queries) new Queries().toBean(config.getQueries());
        this.response = new Response().toBean(config.getResponse());
        return this.toJson();
    }

    private JSONObject toJson() {
        JSONObject requestJson = new JSONObject(true);
        requestJson.put("uri", uri);
        requestJson.put("method", method.name().toLowerCase());

        requestJson.put("headers", headers.toConfig());
        requestJson.put("queries", queries.toConfig());
        requestJson.put("xpath", xpath.toConfig());

        JSONObject json = new JSONObject(true);
        json.put("request", requestJson);
        json.put("response", response.toConfig());
        json.put("description", description == null ? "" : description);
        return json;
    }
}