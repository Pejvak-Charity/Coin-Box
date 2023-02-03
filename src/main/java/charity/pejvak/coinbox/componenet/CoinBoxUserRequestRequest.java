package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CoinBoxUserRequestRequest {

    int addressId;
    private String requestType;

    @Nullable
    private String coinBoxType;

    @Nullable
    private long coinBoxId;

    private LocalDateTime preferredDateTime;

}
