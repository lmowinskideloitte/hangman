package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
//    TODO: mala litera
    private String GuessWord = "";
//    TODO: uzyj interfejsu
    private ArrayList<Character> correctGuesses = new ArrayList<>();
    private ArrayList<Character> wrongGuesses = new ArrayList<>();
    private int lives = 6;
    private int defaultLives = 6;
    private String filename = "./words.txt";

//    TODO: priv
    protected void setLives(int lives) {
        this.lives = lives;
    }

    protected void setFilename(String filename) {
        this.filename = filename;
    }

    private void getRandomWord(String wordBankFilename) throws IOException {
        ArrayList<String> wordList = FilesHandler.parseFile(wordBankFilename);
//        TODO: zapisz Rand
        this.GuessWord = wordList.get(new Random().nextInt(wordList.size()));
    }

    private boolean gameEnded() {
        return this.lives <= 0 || GameLogic.hasGuessedAll(this.GuessWord, this.correctGuesses);
    }

    private void nextRound() {
        Scanner scan = new Scanner(System.in);
        char guess;
//        TODO: komunikat o inpucie
        do {
            System.out.print("make your guess: ");
            guess = scan.next().toUpperCase().charAt(0);
        }
        while (GameLogic.alreadyGiven(guess, this.wrongGuesses) || !Character.isLetterOrDigit(guess));
        if (GameLogic.isCorrectGuess(this.GuessWord, guess)) {
            this.correctGuesses.add(guess);
        } else {
//            TODO: lives--
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
        System.out.print("""
                [S]tart new game
                [A]dd word to wordbank
                [E]xit
                [C]hange wordbank path
                Change [L]ife count
                
                your choice:\s""");
    }

    private void wordAdder() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("type your word: ");
        FilesHandler.addWord(this.filename, scan.next());
    }

    private void wordbankChanger() {
        Scanner scan = new Scanner(System.in);
        System.out.print("type new path: ");
        setFilename(scan.next());
    }

    private void lifeCountChanger() {
        Scanner scan = new Scanner(System.in);
        System.out.print("type new life count: ");
        this.defaultLives = scan.nextInt();
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
                    setLives(this.defaultLives);
                    play();
                }
                case "A" -> wordAdder();
                case "E" -> System.exit(0);
                case "C" -> wordbankChanger();
                case "L" -> lifeCountChanger();
                default -> System.out.println("wrong choice");
            }
        }
    }
}
