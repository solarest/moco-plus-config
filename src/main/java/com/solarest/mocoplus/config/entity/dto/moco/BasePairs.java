package com.solarest.mocoplus.config.entity.dto.moco;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author jinjian
 */
public abstract class BasePairs extends HashMap<String, String> {

    BasePairs() {
        super(8);
    }

    /**
     * to config json
     *
     * @return config for moco
     */
    public abstract JSONObject toConfig();

    public BasePairs toBean(String text) {
        JSONObject json = JSON.parseObject(text);
        if (json != null) {
            json.forEach((k, v) -> this.put(k, String.valueOf(v)));
        }
        return this;
    }

    @Override
    public String toString() {
        return this.toConfig().toJSONString();
    }
}
