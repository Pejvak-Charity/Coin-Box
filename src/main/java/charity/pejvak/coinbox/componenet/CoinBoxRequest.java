package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.enums.CoinBoxType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxRequest {

    private String name;
    private String size;
    private CoinBoxType type;

}
