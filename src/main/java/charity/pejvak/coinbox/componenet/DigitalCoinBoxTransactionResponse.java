package charity.pejvak.coinbox.componenet;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigitalCoinBoxTransactionResponse extends TransactionResponse {

    public String gatewayURL;
}
