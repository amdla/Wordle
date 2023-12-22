package org.example;

import javax.swing.*;

public class MainView {
    private JFrame frame;
    private Game game;
    private GameView gameView;
    private SettingsView settingsView;

    public MainView(Game game, GameView gameView, JFrame frame) {
        this.frame = frame;
        this.game = game;
        this.gameView = gameView;
        this.settingsView = new SettingsView(game, frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void showGameView() {
        frame.setContentPane(gameView.createAndShowGUI());
        frame.revalidate();
    }

    public void showSettingsView() {
        frame.setContentPane(settingsView.createAndShowGUI(frame, this));
        frame.revalidate();
    }
}