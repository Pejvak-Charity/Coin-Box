package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxRequest {
    private String code;
    private Long typeId;
    private LocalDateTime manufactureDate;

}
