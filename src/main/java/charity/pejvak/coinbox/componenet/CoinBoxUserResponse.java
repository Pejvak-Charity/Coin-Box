package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinBoxUserResponse {

    private Long coinBoxId;
    private String coinBoxTitle;
    private Long coinBoxAmount;

}
