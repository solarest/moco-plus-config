package com.solarest.mocoplus.config.manager.impl;

import com.solarest.mocoplus.config.exception.CommandLineException;
import com.solarest.mocoplus.config.manager.CommandLineManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author jinjian
 */
@Slf4j
@Component
public class CommandLineManagerImpl implements CommandLineManager {

    @Override
    public String runCommandLine(String command, String... args) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayOutputStream error = new ByteArrayOutputStream();

        CommandLine cmdLine = new CommandLine(command);
        cmdLine.addArguments(args);

        Executor exec = new DefaultExecutor();
        exec.setExitValues(null);
        exec.setWatchdog(new ExecuteWatchdog(60 * 1000));
        exec.setStreamHandler(new PumpStreamHandler(output, error));

        exec.execute(cmdLine);
        if (error.size() > 0) {
            throw new CommandLineException(error.toString("utf8"));
        }
        return output.toString();
    }

    @Override
    public String showJavaProcess() throws IOException {
        return runCommandLine("jps", "-lmvV");
    }

    @Override
    public String startMoco(String mocoPath, String configPath, String logPath, int port) throws IOException {
        return runCommandLine(String.format("java -jar %s http -c %s -p %s", mocoPath, configPath, port));
    }

    public static void main(String[] args) throws IOException {
        CommandLineManagerImpl manager = new CommandLineManagerImpl();
        System.out.println(manager.showJavaProcess());
    }
}
