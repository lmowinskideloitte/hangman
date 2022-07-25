package org.example;

import java.util.*;

public class Game {
    private String guessWord = "";
    private List<Character> correctGuesses = new ArrayList<>();
    private List<Character> wrongGuesses = new ArrayList<>();
    private int lives = 6;
    private int defaultLives = 6;
    private String filename = "./words.txt";
    private WordsDao wordsDao = WordsDaoDbImpl.getInstance();

    private void setLives(int lives) {
        this.lives = lives;
    }

    protected void setFilename(String filename) {
        this.filename = filename;
    }

    private void getRandomWord() {
        List<String> wordList = wordsDao.getWords();
//        TODO: zapisz Rand
        this.guessWord = wordList.get(new Random().nextInt(wordList.size()));
    }

    private boolean gameEnded() {
        return this.lives <= 0 || GameLogic.hasGuessedAll(this.guessWord, this.correctGuesses);
    }

    private void nextRound() {
        Scanner scan = new Scanner(System.in);
        char guess;
//        TODO: komunikat o inpucie
        do {
            guess = scan.next().toUpperCase().charAt(0);
        }
        while (GameLogic.alreadyGiven(guess, this.wrongGuesses) || !Character.isLetterOrDigit(guess));
        if (GameLogic.isCorrectGuess(this.guessWord, guess)) {
            this.correctGuesses.add(guess);
        } else {
            this.wrongGuesses.add(guess);
            lives--;
        }
    }

    private void endScreen() {
        System.out.println(
                GameLogic.hasGuessedAll(this.guessWord, this.correctGuesses) ? "YOU'VE WON!!!1\n" : "GIT GUD LOL\n"
        );
    }
    private void play() {
        getRandomWord();
        do {
            BoardPrinter.printHangman(this.lives);
            BoardPrinter.printGuess(this.guessWord, this.correctGuesses, this.wrongGuesses);
            nextRound();
        }
        while (!gameEnded());
        BoardPrinter.printHangman(this.lives);
        BoardPrinter.printWord(this.guessWord);
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

    private void wordAdder() {
        Scanner scan = new Scanner(System.in);
        System.out.print("type your word: ");
        wordsDao.addWord(scan.next());
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
    protected void initialise() {
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
