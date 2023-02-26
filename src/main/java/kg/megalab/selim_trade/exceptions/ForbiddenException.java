package kg.megalab.selim_trade.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super("forbidden");
    }

    public ForbiddenException(String message, Throwable cause) {
        super("forbidden", cause);
    }
}
