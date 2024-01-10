package ui;

import mancala.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.IOException;

public class MancalaGUI extends JFrame {
    private JButton[][] pitButtons;
    private JLabel player1StoreLabel;
    private JLabel player2StoreLabel;
    private JButton newGameButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;
    private JLabel currentPlayerLabel;
    private MancalaGame mancalaGame;
    private Player player1;
    private Player player2;
    private JLabel player1StatsLabel;
    private JLabel player1KalahGamesPlayedLabel;
    private JLabel player1AyoGamesPlayedLabel;
    private JLabel player1KalahGamesWonLabel;
    private JLabel player1AyoGamesWonLabel;
    private JLabel player2StatsLabel;
    private JLabel player2KalahGamesPlayedLabel;
    private JLabel player2AyoGamesPlayedLabel;
    private JLabel player2KalahGamesWonLabel;
    private JLabel player2AyoGamesWonLabel;

    public MancalaGUI(GameRules gameRules) {
        mancalaGame = new MancalaGame();
        mancalaGame.setBoard(gameRules);
        String player1Name = JOptionPane.showInputDialog(null, "Enter Player 1's name:");
        String player2Name = JOptionPane.showInputDialog(null, "Enter Player 2's name:");
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        mancalaGame.setPlayers(player1, player2);
        initializeComponents();
        createLayout();
        createListeners();
        setPreferredSize(new Dimension(1000, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        pitButtons = new JButton[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                try {
                    pitButtons[i][j] = new JButton(("Pit " + (i * 6 + j + 1) + ": " + mancalaGame.getNumStones(i * 6 + j + 1)));
                } catch (PitNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            player1StoreLabel = new JLabel("Player 1's Store: " + mancalaGame.getStoreCount(player1));
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }

        player1StatsLabel = new JLabel("Player 1 Statistics");
        // Add statistics labels to display
        player1KalahGamesPlayedLabel = new JLabel("Kalah Games Played: " + player1.getUserProfile().getKalahGamesPlayed());
        player1AyoGamesPlayedLabel = new JLabel("Ayo Games Played: " + player1.getUserProfile().getAyoGamesPlayed());
        player1KalahGamesWonLabel = new JLabel("Kalah Games Won: " + player1.getUserProfile().getGamesWonInKalah());
        player1AyoGamesWonLabel = new JLabel("Ayo Games Won: " + player1.getUserProfile().getGamesWonInAyo());

        try {
            player2StoreLabel = new JLabel("Player 2's Store: " + mancalaGame.getStoreCount(player2));
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }

        player2StatsLabel = new JLabel("Player 2 Statistics");
        player2KalahGamesPlayedLabel = new JLabel("Kalah Games Played: " + player2.getUserProfile().getKalahGamesPlayed());
        player2AyoGamesPlayedLabel = new JLabel("Ayo Games Played: " + player2.getUserProfile().getAyoGamesPlayed());
        player2KalahGamesWonLabel = new JLabel("Kalah Games Won: " + player2.getUserProfile().getGamesWonInKalah());
        player2AyoGamesWonLabel = new JLabel("Ayo Games Won: " + player2.getUserProfile().getGamesWonInAyo());

        newGameButton = new JButton("New Game");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        quitButton = new JButton("Quit");
        currentPlayerLabel = new JLabel();
        updateCurrentPlayerLabel();
    }

    private void createLayout() {
        JPanel boardPanel = new JPanel(new GridLayout(2, 6));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                boardPanel.add(pitButtons[i][j]);
            }
        }

        JPanel statsPanel = new JPanel(new GridLayout(10, 1));
        statsPanel.add(player1StoreLabel);
        statsPanel.add(player1StatsLabel);
        statsPanel.add(player1KalahGamesPlayedLabel);
        statsPanel.add(player1AyoGamesPlayedLabel);
        statsPanel.add(player1KalahGamesWonLabel);
        statsPanel.add(player1AyoGamesWonLabel);

        JPanel statsPanel2 = new JPanel(new GridLayout(10, 1));
        statsPanel2.add(player2StoreLabel);
        statsPanel2.add(player2StatsLabel);
        statsPanel2.add(player2KalahGamesPlayedLabel);
        statsPanel2.add(player2AyoGamesPlayedLabel);
        statsPanel2.add(player2KalahGamesWonLabel);
        statsPanel2.add(player2AyoGamesWonLabel);

        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(newGameButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quitButton);
        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(currentPlayerLabel);
        controlPanel.add(buttonPanel, BorderLayout.NORTH);
        controlPanel.add(labelPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(statsPanel, BorderLayout.EAST);
        add(statsPanel2, BorderLayout.WEST);
    }

    private void createListeners() {
        newGameButton.addActionListener(e -> handleNewGame(e));
        saveButton.addActionListener(e -> handleSave(e));
        loadButton.addActionListener(e -> handleLoad(e));
        quitButton.addActionListener(e -> handleQuit(e));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                pitButtons[i][j].addActionListener(e -> handlePitClick(e));
            }
        }
    }

    private void handleNewGame(ActionEvent event) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game?", "New Game Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String player1Name = JOptionPane.showInputDialog(null, "Enter Player 1's name:");
            String player2Name = JOptionPane.showInputDialog(null, "Enter Player 2's name:");
            player1 = new Player(player1Name);
            player2 = new Player(player2Name);
            mancalaGame.setPlayers(player1, player2);
            mancalaGame.startNewGame();
            updateUI();
            currentPlayerLabel.setText("Current Player: " + mancalaGame.getCurrentPlayer().getName());
        }
    }

    private void handleSave(ActionEvent event) {
        try {
            Saver.saveObject(mancalaGame, "mancalaGame.ser");
            Saver.saveObject(player1.getUserProfile(), "player1Profile.ser");
            Saver.saveObject(player2.getUserProfile(), "player2Profile.ser");
            JOptionPane.showMessageDialog(this, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            handleSaveError(e);
        }
    }

    private void handleLoad(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser("assets/");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                loadMancalaGame(fileChooser.getSelectedFile().getName());
                loadUserProfiles();
                updateUI();
                JOptionPane.showMessageDialog(this, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(); // Print the stack trace for debugging
                handleLoadError(e);
            }
        }
    }

    private void loadMancalaGame(String fileName) throws IOException, ClassNotFoundException {
        mancalaGame = (MancalaGame) Saver.loadObject(fileName);
    }

    private void loadUserProfiles() throws IOException, ClassNotFoundException {
        player1 = Player.createPlayerFromProfile((UserProfile) Saver.loadObject("player1Profile.ser"));
        player2 = Player.createPlayerFromProfile((UserProfile) Saver.loadObject("player2Profile.ser"));
    }

    private void handlePitClick(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();
        String buttonText = clickedButton.getText();
        int pitNumber = extractPitNumber(buttonText);
        handleMove(pitNumber);
    }

    private int extractPitNumber(String buttonText) {
        String[] parts = buttonText.split(":");
        String pitInfo = parts[0].trim(); // Get the part before the ':'
        String[] pitInfoParts = pitInfo.split(" ");
        return Integer.parseInt(pitInfoParts[1]); // Extract the pit number
    }

    private void handleMove(int selectedPit) {
        try {
            mancalaGame.move(selectedPit);
            updateUI();
            if (mancalaGame.isGameOver()) {
                handleGameOver();
            } else {
                mancalaGame.switchPlayer();
                currentPlayerLabel.setText("Current Player: " + mancalaGame.getCurrentPlayer().getName());
            }
        } catch (InvalidMoveException e) {
            handleInvalidMoveError();
        }
    }

    private void handleGameOver() {
        Player winner;
        try {
            winner = mancalaGame.getWinner();
        } catch (GameNotOverException e) {
            winner = null;
            e.printStackTrace();
        }
        
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner.getName() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            updatePlayerStatistics(winner, true);
        } else {
            JOptionPane.showMessageDialog(this, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            // Assuming both players are tied in this case
            updatePlayerStatistics(player1, false);
            updatePlayerStatistics(player2, false);
        }
    }
    
    // Add this method to update game statistics for a player
    private void updatePlayerStatistics(Player player, boolean won) {
        player.getUserProfile().updateGameStatistics(mancalaGame.getBoard(), won);
    }


    private void updateUI() {
        updateCurrentPlayerLabel();
        updatePitButtons();
        updateStoreLabels();
        updateStatsLabels();
    }

    private void updatePitButtons() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                try {
                    pitButtons[i][j].setText("Pit " + (i * 6 + j + 1) + ": " + mancalaGame.getNumStones(i * 6 + j + 1));
                } catch (PitNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateStoreLabels() {
        try {
            player1StoreLabel.setText("Player 1's Store: " + mancalaGame.getStoreCount(player1));
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }
        try {
            player2StoreLabel.setText("Player 2's Store: " + mancalaGame.getStoreCount(player2));
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }
    }

    private void updateStatsLabels() {
        // Update Player 1's statistics
        player1KalahGamesPlayedLabel.setText("Kalah Games Played: " + player1.getUserProfile().getKalahGamesPlayed());
        player1AyoGamesPlayedLabel.setText("Ayo Games Played: " + player1.getUserProfile().getAyoGamesPlayed());
        player1KalahGamesWonLabel.setText("Kalah Games Won: " + player1.getUserProfile().getGamesWonInKalah());
        player1AyoGamesWonLabel.setText("Ayo Games Won: " + player1.getUserProfile().getGamesWonInAyo());
    
        // Update Player 2's statistics
        player2KalahGamesPlayedLabel.setText("Kalah Games Played: " + player2.getUserProfile().getKalahGamesPlayed());
        player2AyoGamesPlayedLabel.setText("Ayo Games Played: " + player2.getUserProfile().getAyoGamesPlayed());
        player2KalahGamesWonLabel.setText("Kalah Games Won: " + player2.getUserProfile().getGamesWonInKalah());
        player2AyoGamesWonLabel.setText("Ayo Games Won: " + player2.getUserProfile().getGamesWonInAyo());
    }

    private void updateCurrentPlayerLabel() {
        currentPlayerLabel.setText("Current Player: " + mancalaGame.getCurrentPlayer().getName());
    }

    private void handleInvalidMoveError() {
        JOptionPane.showMessageDialog(this, "Invalid move. Please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
    }

    private void handleSaveError(IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving game.", "Save Game", JOptionPane.ERROR_MESSAGE);
    }

    private void handleLoadError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading game.", "Load Game", JOptionPane.ERROR_MESSAGE);
    }

    private void handleQuit(ActionEvent event) {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to save before quitting?", "Save Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            handleSave(event);
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        String[] options = {"Kalah", "Ayo"};
        int gameChoice = JOptionPane.showOptionDialog(
                null,
                "Choose a game:",
                "Game Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (gameChoice == 0) {
            SwingUtilities.invokeLater(() -> new MancalaGUI(new KalahRules()));
        } else if (gameChoice == 1) {
            SwingUtilities.invokeLater(() -> new MancalaGUI(new AyoRules()));
        } else {
            System.exit(0);
        }
    }
}
