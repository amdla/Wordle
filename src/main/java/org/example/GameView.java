package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

public class GameView {
    private JFrame frame;
    private JTable table;
    private JTextField inputField;
    private GameTableModel tableModel;
    private Game game;

    public GameView(Game game, JFrame frame) {
        this.game = game;
        this.frame = frame;
        createAndShowGUI();
    }

    public void update() {
        switch (game.getGameState()) {
            case WON:
                JOptionPane.showMessageDialog(frame, "Congratulations, you have won!");
                break;
            case LOST:
                JOptionPane.showMessageDialog(frame, "Sorry, you have lost. The word was: " + game.getCurrentWord());
                break;
        }
    }

    public JPanel createAndShowGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create an empty table model
        tableModel = new GameTableModel(0, 5);
        table = new JTable(tableModel);

        // Set row height
        table.setRowHeight(30);

        // Remove column headers
        table.setTableHeader(null);

        // Center text in each cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ColorRenderer());
        }

        // Set column width
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(50);
        }

        // Increase font size
        table.setFont(new Font("Serif", Font.PLAIN, 24));

        // Create input field
        inputField = new JTextField("Guess a word");
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Guess a word")) {
                    inputField.setText("");
                }
            }
        });
        inputField.addActionListener(e -> {
            // Get user's input
            String userInput = inputField.getText();

            // Create a new row with the user's input
            Object[] row = new Object[tableModel.getColumnCount()];
            for (int i = 0; i < userInput.length() && i < row.length; i++) {
                char c = userInput.charAt(i);
                Color color = (c == game.getCurrentWord().charAt(i)) ? Color.GREEN : Color.RED;
                row[i] = new Cell(c, color);
            }

            // Add the new row to the table model
            tableModel.addRow(row);

            // Clear the input field
            inputField.setText("");
        });

        // Create label to show the word to guess
        JLabel wordLabel = new JLabel(game.getCurrentWord());
        wordLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add input field, word label and table to the main panel
        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.CENTER); // Add a gap
        mainPanel.add(wordLabel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(table), BorderLayout.SOUTH);

        return mainPanel;
    }

    class GameTableModel extends DefaultTableModel {
        private List<List<Color>> colors = new ArrayList<>();

        public GameTableModel(int rowCount, int columnCount) {
            super(rowCount, columnCount);
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Cell.class;
        }
    }

    class ColorRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, ((Cell)value).getValue(), isSelected, hasFocus, row, column);
            setBackground(((Cell)value).getColor());
            return this;
        }
    }

    class Cell {
        private char value;
        private Color color;

        public Cell(char value, Color color) {
            this.value = value;
            this.color = color;
        }

        public char getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }
    }
}