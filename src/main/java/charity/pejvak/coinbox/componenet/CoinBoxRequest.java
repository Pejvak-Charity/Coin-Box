package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.enums.CoinBoxType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxRequest {

    private String name;
    private String size;
    private CoinBoxType type;

}
