package kg.megalab.selim_trade.exceptions;

import kg.megalab.selim_trade.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {


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



    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    void forbidden() {
    }

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

//    @ExceptionHandler({NoSuchMethodError.class})
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    public ErrorMessage noSuchMethod(Exception e, WebRequest request) {
//        return new ErrorMessage(
//                HttpStatus.I_AM_A_TEAPOT.value(),
//                new Date(),
//                e.getMessage(),
//                request.getDescription(false)
//        );
//    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorMessage unauthorizedRequest(Excep   tion e, WebRequest request) {
//        return new ErrorMessage(
//                HttpStatus.UNAUTHORIZED.value(),
//                new Date(),
//                e.getMessage(),
//                request.getDescription(false)
//        );
//    }

    @ExceptionHandler({RequestTimeoutException.class})
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    void requestTimeout() {
    }


}


