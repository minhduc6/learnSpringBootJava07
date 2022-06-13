package vn.techmaster.bank.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandleException {
    // Xử lý NotFound
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Xử BadRequest
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Xử BadRequest
    @ExceptionHandler(TradeException.class)
    public ResponseEntity<ErrorMessage> handleTradeException(TradeException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Xử lý các Exception còn lại
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleOtherException(RuntimeException exception){
        ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}