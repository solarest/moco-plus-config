package com.solarest.mocoplus.config.entity.dto.moco;

import com.alibaba.fastjson.JSONObject;

/**
 * @author solar
 */
public abstract class BaseElement {

    /**
     * to config json
     *
     * @return config for moco
     */
    public abstract JSONObject toConfig();
}
