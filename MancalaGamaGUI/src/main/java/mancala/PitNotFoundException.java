package mancala;

public class PitNotFoundException extends Exception {
    // Generated serialVersionUID for versioning control
    private static final long serialVersionUID = 1L;

    public PitNotFoundException() {
        super("Pit not found on the game board.");
    }

    public PitNotFoundException(final String message) {
        super(message);
    }
}
