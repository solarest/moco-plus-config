package com.solarest.mocoplus.config.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.solarest.mocoplus.config.entity.dto.MocoConfig;
import com.solarest.mocoplus.config.entity.dto.moco.Response;
import com.solarest.mocoplus.config.entity.po.MocoRequestConfig;
import com.solarest.mocoplus.config.service.MocoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author JinJian
 */
@RestController
@RequestMapping("/config")
public class MocoConfigController extends BaseController {

    private final MocoConfigService configService;

    @Autowired
    public MocoConfigController(MocoConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String importConfig(@RequestBody String input) {
        JSONArray array = JSONArray.parseArray(input);
        return response(configService.importConfig(array));
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String exportConfig() {
        return response(configService.exportConfig());
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(
            @RequestParam String uri,
            @RequestParam String description
    ) {
        MocoRequestConfig config = new MocoRequestConfig();
        config.setUri(uri);
        config.setDescription(description);
        return response(configService.searchConfig(config));
    }

    @RequestMapping(value = "/convertFormat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertFormat(@RequestBody String input) {
        MocoConfig mocoConfig = new MocoConfig().toBean(JSON.parseObject(input));
        return response(Response.convert(mocoConfig.getResponse()).toConfig());
    }
}
