package org.example;

import javax.swing.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static final int MAX_GUESSES = 7;

    private String currentWord;
    private final List<String> guesses;
    private GameState gameState;
    private final List<String> words;
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
        WordLoader wordLoader = new WordLoader();
        this.words = wordLoader.loadWords();
        this.guesses = new ArrayList<>();
        this.currentWord = getRandomWord();
        this.gameState = GameState.LOST;
        this.cellColorManager = new CellColorManager(currentWord);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public boolean isNotWord(String guess) {
        return !words.contains(guess);
    }

    public void guess(String guess) {
        if (isNotWord(guess)) {
            JOptionPane.showMessageDialog(null, "The input is not an actual word. uwu");
            return;
        }

        guesses.add(guess);

        if (guess.equals(currentWord)) {
            gameState = GameState.WON;
            JOptionPane.showMessageDialog(null, "Congratulations, you have won!");
            gameView.disableInput();
        } else if (guesses.size() == MAX_GUESSES) {
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

}