package com.solarest.mocoplus.config.entity.po;

import com.solarest.mocoplus.config.entity.dto.MocoConfig;
import com.solarest.mocoplus.config.enumeration.HttpMethod;
import lombok.Data;

/**
 * @author JinJian
 */
@Data
public class MocoRequestConfig {

    private String uri;

    private HttpMethod method;

    private String headers;

    private String xpath;

    private String queries;

    private String response;

    private String description;

    private String hash;

    public MocoRequestConfig buildFromMocoConfig(MocoConfig config) {
        this.uri = config.getUri();
        this.description = config.getDescription();
        this.method = config.getMethod();
        this.headers = config.getHeaders() == null ? "" : config.getHeaders().toString();
        this.xpath = config.getXpath() == null ? "" : config.getXpath().toString();
        this.queries = config.getQueries() == null ? "" : config.getQueries().toString();
        this.response = config.getResponse().toConfig().toJSONString();
        return this;
    }
}
