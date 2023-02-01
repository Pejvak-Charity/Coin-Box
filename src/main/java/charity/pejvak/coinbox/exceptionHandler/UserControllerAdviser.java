package charity.pejvak.coinbox.exceptionHandler;

import charity.pejvak.coinbox.componenet.Error;
import charity.pejvak.coinbox.exception.NoSuchUserExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class UserControllerAdviser {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleConstraintViolation(DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Error.builder()
                        .message(ex.getMessage())
                        .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .localDateTime(LocalDateTime.now())
                        .internalCode(422)
                        .build());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchUserExistsException.class)
    public ResponseEntity<Error> userNotFoundHandler(NoSuchUserExistsException e){
        Error error = Error.builder().code(404).localDateTime(LocalDateTime.now())
                .internalCode(404)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(404).body(error);
    }

}
