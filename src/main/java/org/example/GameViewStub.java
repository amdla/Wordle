package org.example;

import javax.swing.*;
//class used just for testing
class GameViewStub extends GameView {
    public GameViewStub(Game game, JFrame frame) {
        super(game, frame);
    }

    @Override
    public void disableInput() {
        // Do nothing
    }
}