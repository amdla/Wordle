package org.example;

import javax.swing.*;

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
    private CellColorManager cellColorManager;

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
        this.gameState = GameState.LOST;
        this.cellColorManager = new CellColorManager(currentWord);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public boolean isActualWord(String guess) {
        return words.contains(guess);
    }

    public void guess(String guess) {
        if (!isActualWord(guess)) {
            JOptionPane.showMessageDialog(null, "The input is not an actual word. uwu");
            return;
        }

        guesses.add(guess);

        if (guess.equals(currentWord)) {
            gameState = GameState.WON;
            JOptionPane.showMessageDialog(null, "Congratulations, you have won!");
            gameView.disableInput();
        } else if (guesses.size() == 7) {
            gameState = GameState.LOST;
            JOptionPane.showMessageDialog(null, "Sorry, you have lost. The word was: " + currentWord);
            gameView.disableInput();
        } else {
            if (gameView != null) {
                gameView.update();
            }
        }
    }

    public void reset() {
        guesses.clear();
        currentWord = getRandomWord();
        gameState = GameState.LOST;
        cellColorManager = new CellColorManager(currentWord);
        gameView.setCellColorManager(cellColorManager);
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