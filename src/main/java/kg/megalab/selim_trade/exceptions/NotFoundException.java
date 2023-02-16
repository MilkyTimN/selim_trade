package kg.megalab.selim_trade.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super("not found");
    }

    public NotFoundException(String message, Throwable cause) {
        super("not found", cause);
    }
}
