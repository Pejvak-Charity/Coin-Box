package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalCoinBoxTransactionResponse {

    public String gatewayURL;
}
