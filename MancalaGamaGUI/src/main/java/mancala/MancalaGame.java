package mancala;

import java.io.Serializable;
import java.util.ArrayList;

public class MancalaGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private GameRules gameRules;
    private Player gameCurrentPlayer;
    private final ArrayList<Player> gamePlayers;
    Store store = new Store();

    public MancalaGame() {
        gameCurrentPlayer = null;
        gamePlayers = new ArrayList<>();

        // Initialize players
        final Player player1 = new Player("Player 1");
        final Player player2 = new Player("Player 2");

        // Set players for the game
        setPlayers(player1, player2);
    }

    public GameRules getBoard() {
        return gameRules;
    }

    public Player getCurrentPlayer() {
        return gameCurrentPlayer;
    }

    public int getNumStones(final int pitNum) throws PitNotFoundException {
        if (pitNum < 0 || pitNum > 12) {
            throw new PitNotFoundException();
        }
        return gameRules.getNumStones(pitNum);
    }

    /* Gets the players for the game */
    public ArrayList<Player> getPlayers() {
        return gamePlayers;
    }

    public int getStoreCount(final Player player) throws NoSuchPlayerException {
        //gameRules.getDataStructure();
        int num;
        
        if (!gamePlayers.contains(player)) {
            throw new NoSuchPlayerException("Player not found.");
        }

        if (player == getPlayers().get(0)) {
            num = 1;
        } else {
            num = 2;
        }

        return gameRules.getStoreCount(num);
    }

    public Player getWinner() throws GameNotOverException {
        final int play1 = gameRules.getStoreCount(1);
        final int play2 = gameRules.getStoreCount(2);
        Player winner;

        if (play1 > play2) {
            winner = getPlayers().get(0);
        } else if (play2 > play1) {
            winner = getPlayers().get(1);
        } else {
            winner = null;
        }

        return winner;
    }

    public boolean isGameOver() {
        boolean ans = false;

        if (gameRules.isSideEmpty(1) || gameRules.isSideEmpty(7)) {
            ans = true;
        }
        return ans; // Assume isSideEmpty checks if a side is empty
    }

    /* Makes a move for the current player. */
    public int move(final int startPit) throws InvalidMoveException {
        int currentPl;
    
        final Player currentPlayer = getCurrentPlayer();
        if (currentPlayer == null) {
            throw new IllegalStateException("Invalid player.");
        }
    
        final int playerSidePits = 7;
        if (currentPlayer.equals(getPlayers().get(0)) && startPit >= playerSidePits) {
            throw new InvalidMoveException("You can't start from an opponent's pit.");
        } else if (currentPlayer.equals(getPlayers().get(1)) && startPit < playerSidePits) {
            throw new InvalidMoveException("You can't start from an opponent's pit.");
        }

        if (gameCurrentPlayer == gamePlayers.get(0)) {
            currentPl = 1;
        } else {
            currentPl = 2;
        }
    
        gameRules.moveStones(startPit, currentPl);
    
        final int stonesAfterMove = countStonesOnPlayerSide(currentPl);
    
        return stonesAfterMove; // stones remaining after the move
    }
    
    // Helper method to count stones on the current player's side
    private int countStonesOnPlayerSide(final int players) {
        final int playerSidePits = 12 / 2;
        int stones = 0;
    
        if (players == 1) {
            for (int i = 1; i <= playerSidePits; i++) {
                stones += gameRules.getNumStones(i);
            }
        } else {
            for (int i = playerSidePits+1; i < playerSidePits * 2; i++) {
                stones += gameRules.getNumStones(i);
            }
        }
    
        return stones;
    }

    public void setBoard(final GameRules theBoard) {
        gameRules = theBoard;
    }

    public void setCurrentPlayer(final Player player) {
        gameCurrentPlayer = player;
    }

    public void switchPlayer() {
        if (gamePlayers.size() == 2) {
            if (gameCurrentPlayer == gamePlayers.get(0)) {
                gameCurrentPlayer = gamePlayers.get(1);
            } else {
                gameCurrentPlayer = gamePlayers.get(0);
            }
        }
    }

    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        gamePlayers.clear();
        gamePlayers.add(onePlayer);
        gamePlayers.add(twoPlayer);
        gameCurrentPlayer = onePlayer;
    }

    public void startNewGame() {
        gameRules.resetBoard();
    }

    @Override
    public String toString() {
        return "Mancala Game:\n"
                + "Board:\n" + gameRules.toString() + "\n"
                + "Current Player: " + gameCurrentPlayer.getName() + "\n"
                + "Players: " + gamePlayers.get(0).getName() + ", " + gamePlayers.get(1).getName();
    }
}