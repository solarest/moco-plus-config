package com.solarest.mocoplus.config.service.impl;

import com.solarest.mocoplus.config.manager.CommandLineManager;
import com.solarest.mocoplus.config.service.MocoOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author solar
 */
@Component
public class MocoOperateServiceImpl implements MocoOperateService {

    private final CommandLineManager commandLineManager;

    @Autowired
    public MocoOperateServiceImpl(CommandLineManager commandLineManager) {
        this.commandLineManager = commandLineManager;
    }

    @Override
    public void run(String mocoPath, String configurationPath, String logPath, int port) throws IOException {
        commandLineManager.startMoco(mocoPath, configurationPath, logPath, port);
    }

    @Override
    public void run(String configurationPath, String logPath, int port) throws IOException {
        commandLineManager.startMoco(
                "classpath:lib/moco-runner-0.12.0-standalone.jar",
                configurationPath, logPath, port
        );
    }
}
