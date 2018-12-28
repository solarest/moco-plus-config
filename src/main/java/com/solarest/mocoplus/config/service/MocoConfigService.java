package com.solarest.mocoplus.config.service;

import com.alibaba.fastjson.JSONArray;

/**
 * @author JinJian
 */
public interface MocoConfigService {

    /**
     * 导入配置
     *
     * @param array 配置
     * @return 影响条数
     */
    Integer importConfig(JSONArray array);

    /**
     * 导出配置
     *
     * @return 导出配置
     */
    JSONArray exportConfig();

}
