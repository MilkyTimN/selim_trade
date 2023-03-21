package kg.megalab.selim_trade.exceptions;

import kg.megalab.selim_trade.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestApiExceptionHandler{


    @ExceptionHandler({
            ResourceNotFoundException.class,
            UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(Exception exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }



//    @ExceptionHandler({ForbiddenException.class})
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    void forbidden() {
//    }



    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(Exception exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(MethodArgumentNotValidException exception, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "Bad request",
                exception.getBindingResult().getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList()
                        .toString()
        );
    }



//    @ExceptionHandler({RequestTimeoutException.class})
//    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
//    void requestTimeout() {
//    }


}


