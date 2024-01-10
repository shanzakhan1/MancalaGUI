package mancala;

public class AyoRules extends GameRules {
    private static final long serialVersionUID = 1L;

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        final int stonesInStore = gameBoard.getStoreCount(playerNum);
        final int stonesDistributed = distributeStones (startPit);

        int stoppingPoint;

        if (stonesDistributed < 12) {
            stoppingPoint = (startPit + (stonesDistributed % 12)) % 12;
        } else {
            stoppingPoint = ((startPit + (stonesDistributed % 12)) % 12) - 1;
        }
        
        if (gameBoard.getNumStones(stoppingPoint) == 1) {
            if (playerNum == 1 && stoppingPoint <= 6) {
                captureStones(stoppingPoint);
            }
            else if (playerNum == 2 && stoppingPoint > 6){
                captureStones(stoppingPoint);
            }
        }

        return gameBoard.getStoreCount(playerNum) - stonesInStore;
    }

    @Override
    public int distributeStones(final int startPit) {
        int totalStones = gameBoard.removeStones(startPit);
        final int currentPlayer = (startPit <= 6) ? 1 : 2;
        // Set the iterator to the starting position, skipping the starting pit
        gameBoard.setIterator(startPit, currentPlayer, true);

        int pitIndex = startPit;
        int stonesLeft = totalStones;
        int pitStones = 1;

        // Iterate through the pits after the move
        while (stonesLeft > 0) {
            gameBoard.next(); // Move to the next pit
            pitIndex++;

            if ((pitIndex == 7 && currentPlayer == 1) ||(pitIndex == 13 && currentPlayer == 2)) {
                gameBoard.addToStore(currentPlayer, 1);
                pitStones = 1;
                stonesLeft--;
            }
            if (pitIndex == 13) {
                pitIndex = 1;
            }
            if (pitIndex == startPit) {
                pitIndex++;
            }
            if (stonesLeft > 0) {
                pitStones = gameBoard.addStones(pitIndex, 1); // Add a stone to the pit
                stonesLeft--;
            }
            if (stonesLeft == 0 && pitStones != 1) {
                gameBoard.removeStones(pitIndex);
                stonesLeft = pitStones;
                totalStones += pitStones;
            }
        }

        return totalStones;
    }

    @Override
    public int captureStones(final int stoppingPoint) {
        final int currentPlayer = (stoppingPoint <= 6) ? 1 : 2;

        final int oppPit = 12 - (stoppingPoint - 1);

        final int oppStones = gameBoard.removeStones(oppPit);

        return gameBoard.addToStore (currentPlayer, oppStones);
    }

}