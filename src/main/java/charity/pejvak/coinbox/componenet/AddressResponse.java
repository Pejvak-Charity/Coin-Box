package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private long addressId;
    private long userId;
    private int provinceId;
    private String provinceTitle;
    private int cityId;
    private String cityTitle;
    private int zoneId;
    private String zoneTitle;
    private String text;
    private String zipCode;
}
