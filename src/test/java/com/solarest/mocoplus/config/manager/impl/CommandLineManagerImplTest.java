package com.solarest.mocoplus.config.manager.impl;

import com.solarest.mocoplus.config.manager.CommandLineManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLineManagerImplTest {

    @Autowired
    private CommandLineManager commandLineManager;

    @Test
    public void runCommandLine() throws IOException, InterruptedException {
        commandLineManager.runCommandLine("pwd");
    }

    @Test
    public void showJavaProcess() {
    }
}