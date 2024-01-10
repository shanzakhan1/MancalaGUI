package mancala;

public class NoSuchPlayerException extends Exception {
    // Generated serialVersionUID for versioning control
    private static final long serialVersionUID = 1L;

    public NoSuchPlayerException() {
        super("No such player exists in the game.");
    }

    public NoSuchPlayerException(final String message) {
        super(message);
    }
}