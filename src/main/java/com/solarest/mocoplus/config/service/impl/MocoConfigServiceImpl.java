package com.solarest.mocoplus.config.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.entity.dto.MocoConfig;
import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import com.solarest.mocoplus.config.exception.SystemException;
import com.solarest.mocoplus.config.mapper.ConfigMapper;
import com.solarest.mocoplus.config.service.MocoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JinJian
 */
@Service
public class MocoConfigServiceImpl implements MocoConfigService {

    private final ConfigMapper configMapper;

    @Autowired
    public MocoConfigServiceImpl(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer importConfig(JSONArray array) {
        AtomicInteger effectedCount = new AtomicInteger();
        array.forEach(x -> {
            MocoConfig config = new MocoConfig().toBean((JSONObject) x);
            effectedCount.addAndGet(configMapper.insertMocoConfig(new MocoRequestConfig().buildFromMocoConfig(config)));
        });
        return effectedCount.get();
    }

    @Override
    public JSONArray exportConfig() {
        JSONArray array = new JSONArray();
        List<MocoRequestConfig> configList = configMapper.listMocoConfig();
        configList.forEach(x -> {
            JSONObject json = new MocoConfig().toJson(x);
            json.put("id", x.getId());
            array.add(json);
        });
        return array;
    }

    @Override
    public JSONArray searchConfig(MocoRequestConfig config) {
        JSONArray array = new JSONArray();
        List<MocoRequestConfig> configList = configMapper.listTargetConfig(config);
        configList.forEach(x -> {
            JSONObject json = new MocoConfig().toJson(x);
            json.put("id", x.getId());
            array.add(json);
        });
        return array;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertConfig(JSONObject configJson) {
        MocoConfig mocoConfig = new MocoConfig().toBean(configJson);
        MocoRequestConfig config = new MocoRequestConfig().buildFromMocoConfig(mocoConfig);
        if (configMapper.checkHash(config.getHash()) > 0) {
            configMapper.insertMocoConfig(config);
        } else {
            throw new SystemException("record is excited!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(JSONObject configJson) {
        MocoConfig mocoConfig = new MocoConfig().toBean(configJson);
        MocoRequestConfig config = new MocoRequestConfig().buildFromMocoConfig(mocoConfig);
        if (configMapper.checkHash(config.getHash()) > 0) {
            configMapper.updateMocoConfig(config);
        } else {
            throw new SystemException("record is excited!");
        }
    }
}
