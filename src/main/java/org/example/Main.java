package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wordle");
            Game game = new Game(); // Create the Game instance first
            GameView gameView = new GameView(game, frame); // Pass the Game instance to the GameView constructor
            JPanel panel = gameView.createAndShowGUI(); // Initialize the GameView and get the main panel
            frame.add(panel); // Add the main panel to the frame
            frame.pack(); // Adjust the frame size to fit the preferred size of its components
            frame.setVisible(true); // Set the JFrame visibility to true
        });
    }
}