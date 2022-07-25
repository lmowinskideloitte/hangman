package org.example;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesHandler {
    protected static List<String> parseFile(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        try(Scanner scan = new Scanner(new File(filename))){
            while(scan.hasNext()){
                String line = StringUtils.strip(scan.nextLine());
                words.add(line);
            }
        }
        return words;
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
