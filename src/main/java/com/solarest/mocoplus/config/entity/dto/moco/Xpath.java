package com.solarest.mocoplus.config.entity.dto.moco;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jinjian
 */
public class Xpath extends BasePairs {

    @Override
    public JSONObject toConfig() {
        JSONObject json = new JSONObject();
        this.forEach(json::put);
        return json.isEmpty() ? null : json;
    }
}
