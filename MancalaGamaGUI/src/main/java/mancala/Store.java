package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable {
    private static final long serialVersionUID = 1L;
    private int totalStones;
    private Player owner;

    // Existing constructor
    public Store() {
        totalStones = 0;
    }

    // Constructor that accepts a Player object
    public Store(final Player owner) {
        this.owner = owner;
    }

    // Implementation of Countable interface methods
    @Override
    public int getStoneCount() {
        return totalStones;
    }

    @Override
    public void addStone() {
        totalStones++;
    }

    @Override
    public void addStones(final int numToAdd) {
        totalStones += numToAdd;
    }

    @Override
    public int removeStones() {
        final int removedStones = totalStones;
        totalStones = 0;
        return removedStones;
    }

    // Additional method to set the owner of the store
    public void setOwner(final Player player) {
        if (owner == null) {
            owner = player;
        }
    }

    // Additional method to get the owner of the store
    public Player getOwner() {
        return owner;
    }

    // Existing toString method
    @Override
    public String toString() {
        return "Store[Owner: " + (owner != null ? owner.getName() : "None") + ", Stones: " + totalStones + "]";
    }
}
