package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static final int MAX_GUESSES = 7;

    private String currentWord;
    private final List<String> guesses;
    private GameState gameState;
    private CellColorManager cellColorManager;
    private final List<String> words;
    private DialogDisplayer dialogDisplayer;
    private GameView gameView;

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
        this.dialogDisplayer = new DialogDisplayer();
        this.cellColorManager = new CellColorManager(currentWord);
    }
    public void setDialogDisplayer(DialogDisplayer dialogDisplayer) {
        this.dialogDisplayer = dialogDisplayer;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public boolean isNotWord(String guess) {
        return !words.contains(guess);
    }

    public void guess(String guess) {
        if (isNotWord(guess)) {
            dialogDisplayer.showMessageDialog(null, "The input is not an actual word.");
            return;
        }

        guesses.add(guess);

        if (guess.equals(currentWord)) {
            gameState = GameState.WON;
            dialogDisplayer.showMessageDialog(null, "Congratulations, you have won!");
            gameView.disableInput();
        } else if (guesses.size() == MAX_GUESSES) {
            gameState = GameState.LOST;
            dialogDisplayer.showMessageDialog(null, "Sorry, you have lost. The word was: " + currentWord);
            gameView.disableInput();
        } else {
            if (gameView != null) {
                gameView.update();
            }
        }
    }
    public void reset() {
        guesses.clear();
        String oldWord = currentWord;
        do {
            currentWord = getRandomWord();
        } while (currentWord.equals(oldWord));
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
    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }
}