package charity.pejvak.coinbox.exceptionHandler;

import charity.pejvak.coinbox.componenet.Error;
import charity.pejvak.coinbox.exception.NoSuchCityException;
import charity.pejvak.coinbox.exception.NoSuchProvinceExistsException;
import charity.pejvak.coinbox.exception.NoSuchZoneExistsException;
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

    @ExceptionHandler(value = NoSuchCityException.class)
    public ResponseEntity<Error> cityNotFound(NoSuchCityException e){

        return ResponseEntity.status(404).body(Error.builder().localDateTime(LocalDateTime.now()).code(404).message(e.getMessage()).build());
    }

 @ExceptionHandler(value = NoSuchZoneExistsException.class)
    public ResponseEntity<Error> zoneNotFound(NoSuchZoneExistsException e){

        return ResponseEntity.status(404).body(Error.builder().localDateTime(LocalDateTime.now()).code(404).message(e.getMessage()).build());
    }

}
