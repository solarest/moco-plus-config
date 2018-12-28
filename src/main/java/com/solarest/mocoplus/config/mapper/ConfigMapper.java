package com.solarest.mocoplus.config.mapper;

import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JinJian
 */
@Mapper
@Component
public interface ConfigMapper {

    /**
     * get all config
     *
     * @return configs
     */
    @Select("SELECT * FROM moco_request_config")
    List<MocoRequestConfig> listMocoConfig();

    /**
     * insert config
     *
     * @param config config
     * @return effected count
     */
    @Insert("INSERT OR IGNORE INTO moco_request_config(" +
            " uri, method, headers, xpath, queries, response, hash " +
            ")VALUES( " +
            " #{config.uri}, #{config.method}, #{config.headers}, #{config.xpath}, #{config.queries}, #{config.response}, #{config.hash})")
    Integer insertMocoConfig(@Param("config") MocoRequestConfig config);
}
