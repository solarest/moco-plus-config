package com.solarest.mocoplus.config.service;

import java.io.IOException;

/**
 * @author JinJian
 */
public interface MocoOperateService {

    void run(String mocoPath, String configurationPath, String logPath, int port) throws IOException;

    void run(String configurationPath, String logPath, int port) throws IOException;

}
