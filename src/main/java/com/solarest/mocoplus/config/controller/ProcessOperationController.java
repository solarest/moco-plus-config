package com.solarest.mocoplus.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.solarest.mocoplus.config.manager.CommandLineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    public ProcessOperationController(CommandLineManager commandLineManager) {
        this.commandLineManager = commandLineManager;
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String show() throws IOException {
        String result = commandLineManager.showJavaProcess();

        JSONArray array = new JSONArray();
        List<String> processes = Arrays.asList(result.split("\n"));
        processes.forEach(x -> {
            JSONObject process = new JSONObject(true);
            process.put("pid", x.split(" ")[0]);
            process.put("description", x.split(" ")[1]);
            process.put("params", x.split(" ", 3)[2]);
            array.add(process);
        });
        return response(array);
    }

}
