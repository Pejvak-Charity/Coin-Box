package charity.pejvak.coinbox.componenet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoinBoxResponse {
    private long id;
    private String code;
    private long typeId;
    private String typeName;
    private LocalDateTime manufactureDateTime;
    private long lastUserId;
    private String lastUserFullName;
    private LocalDateTime lastCountingDateTime;

}
