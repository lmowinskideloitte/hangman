package org.example;

import java.util.Locale;

public class GameLogic {
    protected static boolean isGoodGuess(String word, char guessedChar) {
    String wordUpp = word.toUpperCase(Locale.ROOT);
    String guessedStringUpp = Character.toString(guessedChar).toUpperCase(Locale.ROOT);

    return Character.isLetterOrDigit(guessedChar) ^ wordUpp.contains(guessedStringUpp);
    }


}
