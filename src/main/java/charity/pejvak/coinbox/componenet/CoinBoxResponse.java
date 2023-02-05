package charity.pejvak.coinbox.componenet;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoinBoxResponse {
    private long id;
    private String num;
    private LocalDateTime manufactureDateTime;
    private long lastUserId;
    private String lastUserFullName;
    private LocalDateTime lastCountingDateTime;
    private String thumbnailURL;

}
