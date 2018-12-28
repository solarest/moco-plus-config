package com.solarest.mocoplus.config.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author jinjian
 */
public class FileUtil {

    public static String readFile(String path) {
        InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream(String.format("%s", path));
        return new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static void writeFile(String text, String path) {

    }
}
