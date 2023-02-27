package kg.megalab.selim_trade.exceptions;

public class RequestTimeoutException extends RuntimeException {
    public RequestTimeoutException(String message) {
        super("request timeout");
    }

    public RequestTimeoutException(String message, Throwable cause) {
        super("request timeout", cause);
    }
}
