package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable {
    private static final long serialVersionUID = 1L;
    private int stoneCount;

    // Existing constructor
    public Pit() {
        stoneCount = 0;
    }

    // Implementation of Countable interface methods
    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    @Override
    public void addStone() {
        stoneCount++;
    }

    @Override
    public void addStones(final int numToAdd) {
        stoneCount += numToAdd;
    }

    @Override
    public int removeStones() {
        final int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }

    // Existing toString method
    @Override
    public String toString() {
        return "Pit: " + stoneCount + " stones";
    }
}
