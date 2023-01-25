package charity.pejvak.coinbox.exceptionHandler;

import charity.pejvak.coinbox.componenet.Error;
import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class AreaControllerAdviser {

    @ExceptionHandler(value = NoSuchProvinceExistsException.class)
    public ResponseEntity<Error> provinceNotFound(NoSuchProvinceExistsException e){

        return ResponseEntity.status(404).body(Error.builder().localDateTime(LocalDateTime.now()).code(404).message(e.getMessage()).build());
    }

}
