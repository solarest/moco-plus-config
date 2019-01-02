package com.solarest.mocoplus.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.manager.CommandLineManager;
import com.solarest.mocoplus.config.service.impl.MocoOperateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author solar
 */
@RestController
@RequestMapping("/process")
public class ProcessOperationController extends BaseController {

    private final CommandLineManager commandLineManager;

    private final MocoOperateServiceImpl mocoOperateService;

    @Autowired
    public ProcessOperationController(CommandLineManager commandLineManager, MocoOperateServiceImpl mocoOperateService) {
        this.commandLineManager = commandLineManager;
        this.mocoOperateService = mocoOperateService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String show() throws IOException {
        String result = commandLineManager.showJavaProcess();

        JSONArray array = new JSONArray();
        List<String> processes = Arrays.asList(result.split("\n"));
        processes.forEach(x -> {
            JSONObject process = new JSONObject(true);
            String[] args = x.split(" ", 3);
            int length = args.length;

            process.put("pid", length > 0 ? args[0] : "");
            process.put("description", length > 1 ? args[1] : "");
            process.put("params", length > 2 ? args[2] : "");
            array.add(process);
        });
        return response(array);
    }

    @RequestMapping(value = "/launchMoco", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String launchMoco(
            @RequestParam String mocoPath,
            @RequestParam String configurationPath,
            @RequestParam String logPath,
            @RequestParam Integer port
    ) throws IOException {
        mocoOperateService.run(mocoPath, configurationPath, logPath, port);
        return responseSuccess();
    }

}
