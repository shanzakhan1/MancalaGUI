package mancala;

public class InvalidMoveException extends Exception {
    // Generated serialVersionUID for versioning control
    private static final long serialVersionUID = 1L;

    public InvalidMoveException() {
        super("You made an invalid move. Please try again.");
    }

    public InvalidMoveException(final String message) {
        super(message);
    }
}
