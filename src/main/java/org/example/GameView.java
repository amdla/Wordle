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
    private CellColorManager cellColorManager;
    private List<List<Color>> cellColors;

    public GameView(Game game, JFrame frame) {
        this.game = game;
        this.frame = frame;
        this.cellColorManager = new CellColorManager(game.getCurrentWord());
        this.cellColors = new ArrayList<>();
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
        table = new ColorTable(tableModel, cellColors);

        // Set row height
        table.setRowHeight(30);

        // Remove column headers
        table.setTableHeader(null);

        // Center text in each cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
            List<Color> rowColors = new ArrayList<>();
            for (int i = 0; i < userInput.length() && i < row.length; i++) {
                row[i] = String.valueOf(userInput.charAt(i));
                Color color = cellColorManager.getColor(userInput.charAt(i), i);
                rowColors.add(color);
            }

            // Add the new row to the table model
            tableModel.addRow(row);
            cellColors.add(rowColors);

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

        public void addRow(Object[] rowData, String userInput) {
            super.addRow(rowData);

            List<Color> rowColors = new ArrayList<>();
            for (int i = 0; i < userInput.length() && i < rowData.length; i++) {
                Color color = cellColorManager.getColor(userInput.charAt(i), i);
                rowColors.add(color);
            }
            colors.add(rowColors);
        }

    }

    class ColorRenderer extends DefaultTableCellRenderer {
        private Color color;

        public ColorRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(color);
            return this;
        }
    }
    class ColorTable extends JTable {
        private List<List<Color>> colors;

        public ColorTable(DefaultTableModel tableModel, List<List<Color>> colors) {
            super(tableModel);
            this.colors = colors;
        }

        @Override
        public TableCellRenderer getCellRenderer(int row, int column) {
            Color color = colors.get(row).get(column);
            return new ColorRenderer(color);
        }
    }
}