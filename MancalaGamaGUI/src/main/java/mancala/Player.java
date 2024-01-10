package mancala;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private UserProfile userProfile;
    private Store playerStore;

    /* Initializes a new player */
    public Player() {
        this.userProfile = new UserProfile("Player");
        this.playerStore = new Store();
    }

    /* Initializes a new player with a name */
    public Player(final String name) {
        this.userProfile = new UserProfile(name);
        this.playerStore = new Store();
    }

    public static Player createPlayerFromProfile(final UserProfile userProfile) {
        final Player player = new Player();
        player.setUserProfile(userProfile);
        return player;
    }

    public void setUserProfile(final UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /* Gets the name of the player from the user profile */
    public String getName() {
        return userProfile.getUsername();
    }

    /* Gets the user profile of the player */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /* Gets the player's store where they collect stones */
    public Store getStore() {
        return playerStore;
    }

    /* Gets the count of the number of stones in the player's store */
    public int getStoreCount() {
        return playerStore.getStoneCount();
    }

    /* Sets the player's name through the user profile */
    public void setName(final String name) {
        userProfile.setUsername(name);
    }

    /* Sets the player's store */
    public void setStore(final Store store) {
        this.playerStore = store;
    }

    /* Generate a user-friendly string representation of the player */
    @Override
    public String toString() {
        return "Player: " + userProfile.getUsername() + ", Store Count: " + playerStore.getStoneCount();
    }
}
