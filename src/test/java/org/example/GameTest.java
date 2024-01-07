package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setDialogDisplayer(new DialogDisplayer() {
            @Override
            public void showMessageDialog(java.awt.Component parentComponent, Object message) {
                // Do nothing
            }
        });
        GameView gameViewStub = new GameViewStub(game, new JFrame());
        game.setGameView(gameViewStub);
    }

    @Test
    void shouldCheckIfIsNotWord() {
        assertTrue(game.isNotWord("xxxxx"));
        assertFalse(game.isNotWord("river"));
    }
    @Test
    void shouldCorrectlyReset() {
        game.reset();
        assertEquals(Game.GameState.LOST, game.getGameState());
        assertNotEquals("xxxxx", game.getCurrentWord());
    }
    @Test
    void shouldHandleCorrectGuess() {
        game.setCurrentWord("river");
        game.guess("river");
        assertEquals(Game.GameState.WON, game.getGameState());
    }
    @Test
    void shouldHandleInvalidWord() {
        game.setCurrentWord("river");
        game.guess("xxxxx");
        assertEquals(Game.GameState.LOST, game.getGameState());
    }

    @Test
    void shouldHandleInvalidGuess() {
        game.setCurrentWord("river");
        game.guess("ocean");
        assertEquals(Game.GameState.LOST, game.getGameState());
    }

    @Test
    void shouldHandleLoseLoseByTries() {
        game.setCurrentWord("river");
        for (int i = 0; i < Game.MAX_GUESSES; i++) {
            game.guess("punch");
        }
        assertEquals(Game.GameState.LOST, game.getGameState());
    }
}

