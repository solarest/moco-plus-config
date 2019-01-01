package com.solarest.mocoplus.config.manager.impl;

import com.solarest.mocoplus.config.exception.CommandLineException;
import com.solarest.mocoplus.config.manager.CommandLineManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

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
        Runtime run = Runtime.getRuntime();
        Process p = run.exec(String.format("java -jar %s http -c %s -p %s", mocoPath, configPath, port));

        String error = new BufferedReader(new InputStreamReader(p.getErrorStream())).lines().collect(Collectors.joining("\n"));
        System.err.println(p.exitValue());

        if (error.length() > 0 || !p.isAlive()) {
            throw new CommandLineException("".equals(error) ? "run failed" : error);
        }

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        p.getOutputStream().write(arrayOutputStream.toByteArray());
        return "".equals(arrayOutputStream.toString()) ? "run success" : arrayOutputStream.toString();
    }

    public static void main(String[] args) throws IOException {
        CommandLineManagerImpl manager = new CommandLineManagerImpl();
        System.out.println(manager.showJavaProcess());
    }
}
