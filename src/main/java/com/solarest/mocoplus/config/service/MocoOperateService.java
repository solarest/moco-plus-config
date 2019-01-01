package com.solarest.mocoplus.config.service;

import java.io.IOException;

public interface MocoOperateService {

    String run(String mocoPath, String configurationPath, String logPath, int port) throws IOException;

    String run(String configurationPath, String logPath, int port) throws IOException;

}
