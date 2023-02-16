package kg.megalab.selim_trade.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super("bad request");
    }

    public BadRequestException(String message, Throwable cause) {
        super("bad request", cause);
    }
}
