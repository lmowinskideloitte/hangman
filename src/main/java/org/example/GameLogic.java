package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class GameLogic {
//    TODO: format
    protected static boolean isCorrectGuess(String word, Character guessedChar) {
    String wordUpp = word.toUpperCase(Locale.ROOT);
    String guessedStringUpp = Character.toString(guessedChar).toUpperCase(Locale.ROOT);

    return (Character.isLetterOrDigit(guessedChar) && wordUpp.contains(guessedStringUpp));
    }

    protected static boolean alreadyGiven(Character ch, ArrayList<Character> wrongGuesses) {
//        TODO: contains
        return wrongGuesses.stream().anyMatch(ch::equals);
    }

    protected static boolean hasGuessedAll(String word, ArrayList<Character> goodGuesses) {
        String notLowerAlphaNum = "[^a-z\\d]";
        String[] alphaNumWord = word.toLowerCase().replaceAll(notLowerAlphaNum, "").split("");

        String uqSortedWord = Arrays.stream(alphaNumWord).distinct().sorted().
                collect(Collectors.joining()).toUpperCase();
        String sortedGuesses = goodGuesses.stream().sorted().map(String::valueOf).
                collect(Collectors.joining()).toUpperCase();

        return uqSortedWord.equals(sortedGuesses);
    }

}
