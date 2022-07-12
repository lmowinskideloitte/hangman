package org.example;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Locale;

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
        }
    }

//    protected static void printGuess(String word, Character[] guessedChars) {
//        char[] wordCharUppArr = word.toUpperCase(Locale.ROOT).toCharArray();
//        Character[] wordObjCharArr = ArrayUtils.toObject(wordCharUppArr);
//        String guessedString = new String(ArrayUtils.toPrimitive(guessedChars)).toUpperCase(Locale.ROOT);
//
//        for (Character ch : wordObjCharArr) {
//            // XOR to print special characters (which are obviously a part of guessedString)
//            System.out.printf((Character.isLetterOrDigit(ch) ^ guessedString.indexOf(ch) < 0) ? "%s " : "_ ", ch);
//        }
//        System.out.println("\n" + Arrays.toString(guessedChars));
//    }
    protected static void printGuess(String word, char[] correctChars, char[] wrongChars) {
        char[] wordCharUppArr = word.toUpperCase(Locale.ROOT).toCharArray();
        String correctString = new String(correctChars).toUpperCase(Locale.ROOT);

        for (char ch : wordCharUppArr) {
            System.out.printf((Character.isLetterOrDigit(ch) ^ correctString.indexOf(ch) < 0) ? "%s " : "_ ", ch);
        }
        System.out.println('\n' + String.join(", ", new String(wrongChars).split("")));
    }
}
