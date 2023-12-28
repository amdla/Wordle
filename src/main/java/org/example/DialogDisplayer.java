package org.example;

import javax.swing.*;

public class DialogDisplayer {
    public void showMessageDialog(java.awt.Component parentComponent, Object message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }
}