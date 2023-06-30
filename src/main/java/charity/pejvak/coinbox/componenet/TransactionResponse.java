package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.enums.TransactionStatus;
import charity.pejvak.coinbox.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionResponse {

    private Long id;
    private String referenceCode;
    private LocalDateTime dateTime;
    private Long amount;
    private TransactionStatus status;
    private TransactionType transactionType;
    private Long userId;
    private String description;
}
