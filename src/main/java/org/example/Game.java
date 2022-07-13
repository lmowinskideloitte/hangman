package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
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
        Character guess = scan.next().toUpperCase().charAt(0);

        if (GameLogic.isGoodGuess(this.GuessWord, guess)) {
            this.correctGuesses.add(guess);
        } else {
            this.wrongGuesses.add(guess);
            lives -= 1;
        }
    }

    private void endScreen() {
        System.out.println(
                GameLogic.hasGuessedAll(this.GuessWord, this.correctGuesses) ? "YOU'VE WON!!!1\n" : "GIT GUD LOL\n"
        );
    }
    private void play() throws IOException {
        getRandomWord(this.filename);
        do {
            BoardPrinter.printHangman(this.lives);
            BoardPrinter.printGuess(this.GuessWord, this.correctGuesses, this.wrongGuesses);
            nextRound();
        }
        while (!gameEnded());
        BoardPrinter.printHangman(this.lives);
        BoardPrinter.printWord(this.GuessWord);
        endScreen();
    }

    private void printMenu() {
        System.out.println("""
                [S]tart new game
                [A]dd word to wordbank
                [E]xit
                
                your choice:\040
                """);
    }

    private void wordAdder() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("type your word: ");
        FilesHandler.addWord(this.filename, scan.next());
    }

    private void clearArrays() {
        this.correctGuesses = new ArrayList<>();
        this.wrongGuesses = new ArrayList<>();
    }
    protected void initialise() throws IOException {
        System.out.println("""
                                
                 ██░ ██  ▄▄▄       ███▄    █   ▄████  ███▄ ▄███▓ ▄▄▄       ███▄    █\s
                ▓██░ ██▒▒████▄     ██ ▀█   █  ██▒ ▀█▒▓██▒▀█▀ ██▒▒████▄     ██ ▀█   █\s
                ▒██▀▀██░▒██  ▀█▄  ▓██  ▀█ ██▒▒██░▄▄▄░▓██    ▓██░▒██  ▀█▄  ▓██  ▀█ ██▒
                ░▓█ ░██ ░██▄▄▄▄██ ▓██▒  ▐▌██▒░▓█  ██▓▒██    ▒██ ░██▄▄▄▄██ ▓██▒  ▐▌██▒
                ░▓█▒░██▓ ▓█   ▓██▒▒██░   ▓██░░▒▓███▀▒▒██▒   ░██▒ ▓█   ▓██▒▒██░   ▓██░
                 ▒ ░░▒░▒ ▒▒   ▓▒█░░ ▒░   ▒ ▒  ░▒   ▒ ░ ▒░   ░  ░ ▒▒   ▓▒█░░ ▒░   ▒ ▒\s
                 ▒ ░▒░ ░  ▒   ▒▒ ░░ ░░   ░ ▒░  ░   ░ ░  ░      ░  ▒   ▒▒ ░░ ░░   ░ ▒░
                 ░  ░░ ░  ░   ▒      ░   ░ ░ ░ ░   ░ ░      ░     ░   ▒      ░   ░ ░\s
                 ░  ░  ░      ░  ░         ░       ░        ░         ░  ░         ░\s
                """);
        while (true) {
            printMenu();
            Scanner scan = new Scanner(System.in);
            switch (scan.next().toUpperCase(Locale.ROOT)) {
                case "S" -> {
                    clearArrays();
                    play();
                }
                case "A" -> wordAdder();
                case "E" -> System.exit(0);
                default -> System.out.println("wrong choice");
            }
        }
    }
}
