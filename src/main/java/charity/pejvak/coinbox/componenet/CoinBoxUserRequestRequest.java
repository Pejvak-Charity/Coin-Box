package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinBoxUserRequestRequest {
    private Long coinBoxId;
    private String coinBoxTitle;
    private Long addressId;
    private LocalDateTime preferredDateTime;
}
