package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxTypeResponse {
    private Long id;
    private String name;
    private String size;
    private ImageResponse defaultImage;
}
