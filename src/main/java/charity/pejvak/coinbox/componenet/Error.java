package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Error {

    public Error() {
        localDateTime = LocalDateTime.now();
    }

    private int code;
    private String message;
    private LocalDateTime localDateTime;

}
