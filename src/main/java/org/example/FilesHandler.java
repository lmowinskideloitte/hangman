package org.example;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FilesHandler {
    public static String readFile(String filename) throws IOException {
        try(FileInputStream inputStream = new FileInputStream(filename)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    protected static String[] tokenizeString(String rawStr) {
        return StringUtils.split(StringUtils.strip(rawStr));
    }
}
