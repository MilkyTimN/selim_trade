package kg.megalab.selim_trade.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super("unauthorized");
    }

    public UnauthorizedException(String message, Throwable cause) {
        super("unauthorized", cause);
    }
}
