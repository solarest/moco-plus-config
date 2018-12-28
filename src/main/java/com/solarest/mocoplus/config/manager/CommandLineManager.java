package com.solarest.mocoplus.config.manager;

import java.io.IOException;

public interface CommandLineManager {

    String runCommandLine(String command, String... args) throws IOException, InterruptedException;

    String showJavaProcess() throws IOException;

    String startMoco(String mocoPath, String configPath, String logPath, int port);
}
