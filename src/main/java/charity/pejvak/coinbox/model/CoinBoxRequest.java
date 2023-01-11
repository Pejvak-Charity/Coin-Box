package charity.pejvak.coinbox.model;

import charity.pejvak.coinbox.model.enums.CoinBoxRequestStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity(name = "CoinBoxRequest")
@Table(name = "coin_box_request")
public class CoinBoxRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreatedDate
    @Column(name = "create_date",nullable = false,updatable = false)
    private Date createDate;

    @LastModifiedDate
    @Column(name = "modified_date",nullable = false)
    private Date modifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User requestingUser;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private CoinBoxRequestStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coinbox_id",referencedColumnName = "id")
    private CoinBox coinBox;


}
