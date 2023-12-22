package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellColorManager {
    private String word;
    private List<Letter> letters;

    public CellColorManager(String word) {
        this.word = word;
        reset();
    }
    public void reset() {
        this.letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            Letter letter = findLetter(c);
            if (letter == null) {
                letters.add(new Letter(c));
            } else {
                letter.incrementCount();
            }
        }
    }

    public Color getColor(char c, int index) {
        if (index < word.length() && word.charAt(index) == c) {
            return Color.GREEN;
        } else {
            Letter letter = findLetter(c);
            if (letter != null && letter.getCount() > 0) {
                letter.decrementCount();
                return Color.YELLOW;
            } else {
                return Color.RED;
            }
        }
    }

    private Letter findLetter(char c) {
        for (Letter letter : letters) {
            if (letter.getValue() == c) {
                return letter;
            }
        }
        return null;
    }
}

class Letter {
    private char value;
    private int count;

    public Letter(char value) {
        this.value = value;
        this.count = 1;
    }

    public char getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public void decrementCount() {
        count--;
    }
}