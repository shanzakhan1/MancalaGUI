package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int gamesWonInKalah;
    private int gamesWonInAyo;

    // Constructor
    public UserProfile(final String username) {
        this.username = username;
        resetGameStatistics();
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public int getKalahGamesPlayed() {
        return kalahGamesPlayed;
    }

    public int getAyoGamesPlayed() {
        return ayoGamesPlayed;
    }

    public int getGamesWonInKalah() {
        return gamesWonInKalah;
    }

    public int getGamesWonInAyo() {
        return gamesWonInAyo;
    }

    // Setter method for username
    public void setUsername(final String username) {
        this.username = username;
    }

    public void updateGameStatistics(final GameRules gameType, final boolean won) {
        if (gameType instanceof KalahRules) {
            kalahGamesPlayed++;
            if (won) {
                gamesWonInKalah++;
            }
        } else if (gameType instanceof AyoRules) {
            ayoGamesPlayed++;
            if (won) {
                gamesWonInAyo++;
            }
        }
    }    

    // Reset game statistics to initial values
    public void resetGameStatistics() {
        kalahGamesPlayed = 0;
        ayoGamesPlayed = 0;
        gamesWonInKalah = 0;
        gamesWonInAyo = 0;
    }
}
