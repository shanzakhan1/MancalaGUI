package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    private static final long serialVersionUID = 1L;
    final public MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        gameBoard.setUpPits();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the number of stones in a store.
     *
     * @param pitNum The number of the player.
     * @return The number of stones in the store.
     */
    public int getStoreCount(final int num) {
        return gameBoard.getStoreCount(num);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    public MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(final int pitNum) {
        // This method can be implemented in the abstract class.

        int start, end;
        boolean ans = true;

        if (pitNum >= 1 && pitNum <= 6) {
            start = 0;
            end = 6;
        } else if (pitNum >= 7 && pitNum <= 12) {
            start = 6;
            end = 12;
        } else {
            throw new IllegalArgumentException("Invalid pit number.");
        }

        for (int i = start; i < end; i++) {
            if (gameBoard.getNumStones(i + 1) > 0) {
                ans = false;
            }
        }

        return ans;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */

    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.


        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/

        // Create new stores for each player
        final Store storeOne = new Store(one);
        final Store storeTwo = new Store(two);

        // Set the stores on the board
        gameBoard.setStore(storeOne, 1); // Assuming player 1's store index is 1
        gameBoard.setStore(storeTwo,2); // Assuming player 2's store index is 2

    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.emptyPits();
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("Current Player: ").append(currentPlayer).append("\n");
        result.append("Game Board:\n").append(gameBoard.toString());
        return result.toString();
    }
}
