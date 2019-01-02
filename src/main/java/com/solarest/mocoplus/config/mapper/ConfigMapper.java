package com.solarest.mocoplus.config.mapper;

import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import com.solarest.mocoplus.config.mapper.provider.ConfigMapperProvider;
import org.apache.ibatis.annotations.*;
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
     * get filtered config
     *
     * @param config filters
     * @return configs
     */
    @SelectProvider(type = ConfigMapperProvider.class, method = "listTargetConfig")
    List<MocoRequestConfig> listTargetConfig(@Param("config") MocoRequestConfig config);

    /**
     * insert config
     *
     * @param config config
     * @return effected count
     */
    @Insert("INSERT OR IGNORE INTO moco_request_config(" +
            "   uri, method, headers, xpath, " +
            "   queries, response, description, hash " +
            ")VALUES( " +
            "   #{config.uri}, #{config.method}, #{config.headers}, #{config.xpath}, " +
            "   #{config.queries}, #{config.response}, #{config.description}, #{config.hash})")
    Integer insertMocoConfig(@Param("config") MocoRequestConfig config);

    /**
     * update config
     *
     * @param config config
     * @return effected count
     */
    @Update("UPDATE moco_request_config SET" +
            "   uri=#{config.uri}, method=#{config.method}, headers=#{config.headers}, " +
            "   xpath=#{config.xpath}, queries=#{config.queries}, response=#{config.response}, hash=#{config.hash}" +
            "WHERE id=#{config.id}")
    Integer updateMocoConfig(@Param("config") MocoRequestConfig config);

    /**
     * check hash amount
     *
     * @param hash hash
     * @return count
     */
    @Select("SELECT COUNT(*) FROM moco_request_config WHERE hash=#{hash}")
    Integer checkHash(@Param("hash") String hash);


}
