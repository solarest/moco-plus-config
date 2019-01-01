package com.solarest.mocoplus.config.entity.po;

import com.solarest.mocoplus.config.entity.dto.MocoConfig;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author JinJian
 */
@Data
public class MocoRequestConfig {

    private Integer id;

    private String uri;

    private String method;

    private String headers;

    private String xpath;

    private String queries;

    private String response;

    private String description;

    private String hash;

    public MocoRequestConfig buildFromMocoConfig(MocoConfig config) {
        this.uri = config.getUri();
        this.method = config.getMethod().name();
        this.headers = config.getHeaders() == null ? "" : config.getHeaders().toString();
        this.xpath = config.getXpath() == null ? "" : config.getXpath().toString();
        this.queries = config.getQueries() == null ? "" : config.getQueries().toString();
        this.response = config.getResponse().toConfig().toJSONString();
        // hash is used to remove repeated items
        this.hash = DigestUtils.md5Hex(this.uri + this.headers + this.xpath + this.queries + this.method);
        return this;
    }

}
