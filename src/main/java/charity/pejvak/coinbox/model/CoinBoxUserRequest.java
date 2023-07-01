package charity.pejvak.coinbox.model;

import charity.pejvak.coinbox.model.enums.CoinBoxUserRequestStatus;
import charity.pejvak.coinbox.model.enums.CoinBoxUserRequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "CoinBoxRequest")
@Table(name = "coin_box_request")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CoinBoxUserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;


    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    private Date createDate;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private CoinBoxUserRequestStatus status;

    @Enumerated(EnumType.STRING)
    private CoinBoxUserRequestType type;

    @Column(name = "preferred-user-date-time")
    private LocalDateTime preferredDateTime;

    private Long coinBoxTypeId;
    private Long coinBoxId;


}
