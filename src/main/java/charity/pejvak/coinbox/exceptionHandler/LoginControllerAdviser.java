package charity.pejvak.coinbox.exceptionHandler;

import charity.pejvak.coinbox.componenet.Error;
import charity.pejvak.coinbox.exception.NoSuchUserOTPExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class LoginControllerAdviser {

    @ExceptionHandler(value = NoSuchUserOTPExistsException.class)
    public ResponseEntity<Error> OTPNotFound(NoSuchUserOTPExistsException e){
        return ResponseEntity.status(403).body(Error.builder().localDateTime(LocalDateTime.now()).code(403).message(e.getMessage()).build());
    }

}
