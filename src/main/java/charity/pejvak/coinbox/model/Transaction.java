package charity.pejvak.coinbox.model;

import charity.pejvak.coinbox.model.enums.TransactionType;
import charity.pejvak.coinbox.model.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(generator = "seq_transaction", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_transaction", sequenceName = "seq_transaction", allocationSize = 1)
    private Long id;

    private String referenceCode;

    private LocalDateTime dateTime;

    private Long amount;


    //todo in production mode, plz change enum type to ordinary
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    private String description;


}
