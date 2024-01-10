package mancala;

public class GameNotOverException extends Exception {
    // Generated serialVersionUID for versioning control
    private static final long serialVersionUID = 1L;

    public GameNotOverException() {
        super("The game is not yet over.");
    }

    public GameNotOverException(final String message) {
        super(message);
    }
}
