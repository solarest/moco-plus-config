package com.solarest.mocoplus.config.mapper.provider;

import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author JinJian
 */
public class ConfigMapperProvider {

    public String listTargetConfig(@Param("config") MocoRequestConfig config) {
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("moco_request_config");

            if (!config.getDescription().isEmpty()) {
                WHERE("description like '%${config.description}%'");
            }

            if (config.getUri().isEmpty()) {
                WHERE("uri like '%${config.uri}%'");
            }
        }};
        return sql.toString();
    }

}
