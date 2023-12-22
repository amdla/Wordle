package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {
    private String currentWord;
    private List<String> guesses;
    private GameState gameState;
    private List<String> words;
    private GameView gameView;

    public GameState getGameState() {
        return gameState;
    }

    public enum GameState {
        WON,
        LOST
    }

    public Game() {
        this.guesses = new ArrayList<>();
        this.words = loadWords();
        this.currentWord = getRandomWord();
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void guess(String guess) {
        guesses.add(guess);

        if (guess.equals(currentWord)) {
            gameState = GameState.WON;
        } else if (guesses.size() == 5) {
            gameState = GameState.LOST;
        }

        gameView.update();
    }

    public void reset() {
        guesses.clear();
        currentWord = getRandomWord();
        gameView.update();
    }

    private String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public String getCurrentWord() {
        return currentWord;
    }

    private List<String> loadWords() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load words from file", e);
        }
    }
}