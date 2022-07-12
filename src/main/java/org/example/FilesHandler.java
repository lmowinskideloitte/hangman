package org.example;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FilesHandler {
    private static String readFile(String filename) throws IOException {
        try(FileInputStream inputStream = new FileInputStream(filename)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }

    private static String[] tokenizeString(String rawStr) {
        return StringUtils.split(StringUtils.strip(rawStr));
    }

    protected static String[] readWordsFromFile(String filename) throws IOException {
        return tokenizeString(readFile(filename));
    }

    private static FileWriter ensureFileExists(String path) throws IOException {
        File f = new File(path);
        if (f.getParentFile().mkdirs()) { System.out.println("creating parent directories"); }
        if (f.createNewFile() ) { System.out.println("creating file"); }
        return new FileWriter(f, true);
    }

    protected static void addWord(String filePath, String word) throws IOException {
        try (FileWriter fw = ensureFileExists(filePath);
        PrintWriter pw = new PrintWriter(fw)) {
            pw.println(StringUtils.strip(word));
            System.out.println("word added to the file");
        }

    }
}
