package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxTypeResponse {
    private Long id;
    private String name;
    private String size;
    private List<String> imagePaths = new ArrayList<>();
}
