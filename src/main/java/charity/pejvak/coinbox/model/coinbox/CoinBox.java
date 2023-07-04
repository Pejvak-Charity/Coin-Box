package charity.pejvak.coinbox.model.coinbox;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "coinBox")
@Table(name = "coin-box")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "type-id")
    private CoinBoxType type;
    @Column(name = "code")
    private String code;

    @Column(name = "manufacture-date")
    private LocalDateTime manufactureDate;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

}
