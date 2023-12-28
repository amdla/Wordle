package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellColorManager {
    private final String word;
    private List<Letter> letters;
    private int[] numApps;
    private int[] greenApps;

    public CellColorManager(String word) {
        this.word = word;
        reset();
    }
    public void reset() {
        this.letters = new ArrayList<>();
        numApps = new int[26];
        greenApps = new int[26];
        for (char c : word.toCharArray()) {
            Letter letter = findLetter(c);
            if (letter == null) {
                letters.add(new Letter(c));
            }
            numApps[c - 'a']++;
        }
    }

    public Color getColor(char c, int index, boolean secondPass, String wordToGuess) {
        if (!secondPass) {
            if (index < wordToGuess.length() && wordToGuess.charAt(index) == c) {
                numApps[c - 'a']--;
                greenApps[c - 'a']++;
                return Color.GREEN;
            }
        } else {
            if (numApps[c - 'a'] > 0) {
                numApps[c - 'a']--;
                return Color.YELLOW;
            }
        }

        // Default color if none of the above conditions are met
        return Color.RED;
    }

    private Letter findLetter(char c) {
        for (Letter letter : letters) {
            if (letter.value() == c) {
                return letter;
            }
        }
        return null;
    }
}

record Letter(char value) {}