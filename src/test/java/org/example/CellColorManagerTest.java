package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellColorManagerTest {

    private CellColorManager colorManager;

    @BeforeEach
    void setUp() {
        String word = "cabc";
        colorManager = new CellColorManager(word);
    }

    @Test
    void shouldCorrectlyResetExpectedNumApps() {
        colorManager.reset();
        int[] expectedNumApps = new int[]{1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedNumApps, colorManager.getNumApps());
    }

    @Test
    void shouldCorrectlyMarkGreen() {
        char guessedChar = 'c';
        int index = 3;
        boolean secondPass = false;
        String wordToGuess = "cabc";

        Color resultColor = colorManager.getColor(guessedChar, index, secondPass, wordToGuess);

        assertEquals(Color.GREEN, resultColor);
        int[] expectedNumApps = new int[]{1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedNumApps, colorManager.getNumApps());
    }

    @Test
    void shouldCorrectlyMarkRed() {
        char guessedChar = 'x';
        int index = 0;
        boolean secondPass = false;
        String wordToGuess = "cabc";

        Color resultColor = colorManager.getColor(guessedChar, index, secondPass, wordToGuess);

        assertEquals(Color.RED, resultColor);
        int[] expectedNumApps = new int[]{1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedNumApps, colorManager.getNumApps());
    }

    private void assertArrayEquals(int[] expected, int[] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
