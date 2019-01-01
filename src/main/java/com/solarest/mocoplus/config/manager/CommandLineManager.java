package com.solarest.mocoplus.config.manager;

import java.io.IOException;

/**
 * @author solar
 */
public interface CommandLineManager {

    /**
     * run command lines
     *
     * @param command command
     * @param args    args
     * @return console result
     * @throws IOException          IOException
     * @throws InterruptedException InterruptedException
     */
    String runCommandLine(String command, String... args) throws IOException, InterruptedException;

    /**
     * use jps show processes for JVM
     *
     * @return processes
     * @throws IOException IOException
     */
    String showJavaProcess() throws IOException;

    /**
     * start moco server use command lines
     *
     * @param mocoPath   moco jar path
     * @param configPath configuration path
     * @param logPath    moco server logs path
     * @param port       server port
     * @return launcher result
     */
    String startMoco(String mocoPath, String configPath, String logPath, int port) throws IOException;
}
