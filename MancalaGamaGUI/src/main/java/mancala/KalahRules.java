package mancala;

public class KalahRules extends GameRules {
    private static final long serialVersionUID = 1L;

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        final int stonesInStore = gameBoard.getStoreCount(playerNum);
        final int stonesDistributed = distributeStones (startPit);
        final int temp = gameBoard.getStoreCount(playerNum);
        int stoppingPoint;

        if (stonesDistributed < 12) {
            stoppingPoint = (startPit + (stonesDistributed % 12)) % 12;
            if (temp > stonesInStore) {
                stoppingPoint--;
            }
        } else {
            stoppingPoint = ((startPit + (stonesDistributed % 12)) % 12) - 1;
            if (temp > stonesInStore) {
                stoppingPoint--;
            }
        }
        
        if (gameBoard.getNumStones(stoppingPoint) == 1) {
            if (playerNum == 1 && stoppingPoint <= 6) {
                captureStones(stoppingPoint);
            }
            else if (playerNum == 2 && stoppingPoint > 6) {
                captureStones(stoppingPoint);
            }
        }

        return (gameBoard.getStoreCount(playerNum)) - stonesInStore;
    }

    @Override
    public int distributeStones(final int startPit) {
        final int totalStones = gameBoard.removeStones(startPit);
        final int currentPlayer = (startPit <= 6) ? 1 : 2;
        // Set the iterator to the starting position, skipping the starting pit
        gameBoard.setIterator(startPit, currentPlayer, true);

        int pitIndex = startPit;
        int stonesLeft = totalStones;

        // Iterate through the pits after the move
        while (stonesLeft > 0) {
            gameBoard.next(); // Move to the next pit
            pitIndex++;

            if ((pitIndex == 7 && currentPlayer == 1) ||(pitIndex == 13 && currentPlayer == 2)) {
                gameBoard.addToStore(currentPlayer, 1);
                stonesLeft--;
            }
            if (pitIndex == 13) {
                pitIndex = 1;
            }
            if (stonesLeft > 0) {
                gameBoard.addStones(pitIndex, 1); // Add a stone to the pit
                stonesLeft--;
            }
        }

        return totalStones;
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        final int currentPlayer = (stoppingPoint <= 6) ? 1 : 2;

        final int oppPit = 12 - (stoppingPoint - 1);

        gameBoard.removeStones(stoppingPoint);
        final int oppStones = gameBoard.removeStones(oppPit) + 1;

        return gameBoard.addToStore(currentPlayer, oppStones);
    }

}