package com.solarest.mocoplus.config.controller;


import com.alibaba.fastjson.JSONArray;
import com.solarest.mocoplus.config.service.MocoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
