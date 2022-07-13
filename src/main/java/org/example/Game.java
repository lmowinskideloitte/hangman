package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private String GuessWord = "";
    private ArrayList<Character> correctGuesses = new ArrayList<>();
    private ArrayList<Character> wrongGuesses = new ArrayList<>();
    private int lives = 6;
    private String filename = "./words.txt";

    protected void setLives(int lives) {
        this.lives = lives;
    }

    protected void setFilename(String filename) {
        this.filename = filename;
    }

    private void getRandomWord(String wordBankFilename) throws IOException {
        ArrayList<String> wordList = FilesHandler.parseFile(wordBankFilename);
        this.GuessWord = wordList.get(new Random().nextInt(wordList.size()));
    }

    private boolean gameEnded() {
        return this.lives <= 0 || GameLogic.hasGuessedAll(this.GuessWord, this.correctGuesses);
    }

    private void nextRound() {
        Scanner scan = new Scanner(System.in);
        System.out.print("make your guess: ");
        Character guess = scan.next().charAt(0);

        if (GameLogic.isGoodGuess(this.GuessWord, guess)) {
            this.correctGuesses.add(guess);
        } else {
            this.wrongGuesses.add(guess);
            lives -= 1;
        }
    }

    protected void play() throws IOException {
        getRandomWord(this.filename);
        do {
            nextRound();
            BoardPrinter.printHangman(this.lives);
            BoardPrinter.printGuess(this.GuessWord, this.correctGuesses, this.wrongGuesses);
        }
        while (!gameEnded());
    }
}
