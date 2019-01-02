package com.solarest.mocoplus.config.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;

/**
 * @author JinJian
 */
public interface MocoConfigService {

    /**
     * import
     *
     * @param array config
     * @return effected count
     */
    Integer importConfig(JSONArray array);

    /**
     * export
     *
     * @return config
     */
    JSONArray exportConfig();

    /**
     * search config
     *
     * @param config filter
     * @return configs
     */
    JSONArray searchConfig(MocoRequestConfig config);

    /**
     * insert
     *
     * @param configJson config JSON
     */
    void insertConfig(JSONObject configJson);

    /**
     * update
     *
     * @param configJson config JSON
     */
    void updateConfig(JSONObject configJson);

}
