package org.example;

import java.awt.*;

public class CellColorManager {
    private final String word;
    private int[] numApps;

    public CellColorManager(String word) {
        this.word = word;
        reset();
    }

    public void reset() {
        numApps = new int[26];
        for (char c : word.toCharArray()) {
            numApps[c - 'a']++;
        }
    }

    public Color getColor(char c, int index, boolean secondPass, String wordToGuess) {
        if (!secondPass) {
            if (index < wordToGuess.length() && wordToGuess.charAt(index) == c) {
                numApps[c - 'a']--;
                return Color.GREEN;
            }
        } else {
            if (numApps[c - 'a'] > 0) {
                numApps[c - 'a']--;
                return Color.YELLOW;
            }
        }

        return Color.RED;
    }
}