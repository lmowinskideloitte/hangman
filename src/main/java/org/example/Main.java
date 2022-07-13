package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Testing on Main woo-hoo!!!1
        BoardPrinter.printHangman(6-2);
        BoardPrinter.printGuess("piesek-dripek",
                new ArrayList<>(Arrays.asList('I', 'P')),
                new ArrayList<>(Arrays.asList('A', 'Y'))
        );

        ArrayList<String> words = FilesHandler.parseFile("./words.txt");
        System.out.println(words);
    }
}