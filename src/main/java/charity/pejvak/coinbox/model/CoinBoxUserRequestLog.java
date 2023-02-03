package charity.pejvak.coinbox.model;

import charity.pejvak.coinbox.model.enums.CoinBoxStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity(name = "coinBoxUserRequestLog")
@Table(name = "coin-box-user-request-log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinBoxUserRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coinboxuserrequest_id", referencedColumnName = "id")
    @JsonIgnore
    private CoinBoxUserRequest coinBoxUserRequest;

    @Column(name = "oldCoinBoxRequestJSON")
    private String oldCoinBoxRequestJSON;

    @Enumerated(EnumType.STRING)
    @Column(name = "oldStatus")
    private CoinBoxStatus oldStatus;

    @Column(name = "newCoinBoxRequestJSON")
    @Enumerated(EnumType.STRING)
    private String newCoinBoxRequestJSON;


    @Column(name = "newStatus")
    private CoinBoxStatus newStatus;

    @CreatedDate
    @Column(name = "create-date")
    private LocalDateTime createDateTime;

    @Column(name = "description",length = 500)
    private String description;

}
