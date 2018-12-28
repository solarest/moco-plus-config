package com.solarest.mocoplus.config.entity.dto.moco;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jinjian
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Headers extends BasePairs {

    public static final String CONTENT_TYPE = "content-type";
    public static final String APPLICATION_JSON = "application/json";

    @Override
    public JSONObject toConfig() {
        JSONObject json = new JSONObject();
        this.forEach(json::put);
        return json.isEmpty() ? null : json;
    }
}
