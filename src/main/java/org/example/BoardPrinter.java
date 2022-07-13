package org.example;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class BoardPrinter {
    protected static void printHangman(int livesLeft) {
        switch (livesLeft) {
            case 0 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     / \\  |
                          |
                    =========""");
            case 1 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     /    |
                          |
                    =========""");
            case 2 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                          |
                          |
                    =========""");
            case 3 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                     /|   |
                          |
                          |
                    =========""");
            case 4 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                     /    |
                          |
                          |
                    =========""");
            case 5 -> System.out.println("""
                      +---+
                      |   |
                      O   |
                          |
                          |
                          |
                    =========""");
            case 6 -> System.out.println("""
                      +---+
                      |   |
                          |
                          |
                          |
                          |
                    =========""");
            default -> System.out.println("""
                      +---+
                      |   |
                    extra |
                    lives |
                          |
                          |
                    =========""");
        }
    }

    protected static void printWord(String word) {
        System.out.println(word.toUpperCase());
    }
    protected static void printGuess(String word, ArrayList<Character> correctChars, ArrayList<Character> wrongChars) {
        char[] wordCharUppArr = word.toUpperCase(Locale.ROOT).toCharArray();
        String correctString = CharArrListToString(correctChars).toUpperCase(Locale.ROOT);

        for (char ch : wordCharUppArr) {
            System.out.printf((Character.isLetterOrDigit(ch) ^ correctString.indexOf(ch) < 0) ? "%s " : "_ ", ch);
        }
        System.out.println('\n' + String.join(", ", CharArrListToString(wrongChars).split("")));
    }

    private static String CharArrListToString(ArrayList<Character> al) {
        return al.stream().map(Object::toString).collect(Collectors.joining());
    }
}
