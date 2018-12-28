package com.solarest.mocoplus.config.manager.impl;

import com.solarest.mocoplus.config.manager.CommandLineManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

/**
 * @author jinjian
 */
@Slf4j
@Component
public class CommandLineManagerImpl implements CommandLineManager {

    @Override
    public String runCommandLine(String command, String... args) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayOutputStream error = new ByteArrayOutputStream();

        CommandLine cmdLine = new CommandLine(command);
        cmdLine.addArguments(args);

        Executor exec = new DefaultExecutor();
        exec.setExitValues(null);
        exec.setWatchdog(new ExecuteWatchdog(60 * 1000));
        exec.setStreamHandler(new PumpStreamHandler(output, error));

        try {
            exec.execute(cmdLine);
            return output.toString();
        } catch (Exception e) {
            log.error("some thing wrong with command", e);
            return error.toString();
        }
    }

    @Override
    public String showJavaProcess() {
        return runCommandLine("jps", "-lmvV");
    }

    @Override
    public String startMoco(String mocoPath, String configPath, String logPath, int port) {
        return runCommandLine("nohup java",
                String.format("-jar %s http -p %s -c %s > %s 2>&1 &",
                        mocoPath, configPath, port, logPath));
    }

    public static void main(String[] args) {
        CommandLineManagerImpl manager = new CommandLineManagerImpl();
        System.out.println(manager.showJavaProcess());
    }
}
