package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wordle");
            Game game = new Game();
            GameView gameView = new GameView(game, frame);
            game.setGameView(gameView);
            JPanel panel = gameView.createAndShowGUI();
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}