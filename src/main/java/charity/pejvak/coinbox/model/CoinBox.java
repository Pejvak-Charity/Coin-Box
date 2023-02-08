package charity.pejvak.coinbox.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "coinBox")
@Table(name = "coin-box")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "type-id")
    private CoinBoxType type;


    @Column(name = "code")
    private String code;

    @Column(name = "manufacture-date")
    private LocalDateTime manufactureDate;


    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @OneToMany(mappedBy = "coinBox")
    @JsonIgnore
    private Set<CoinBoxUserRequest> requests = new HashSet<>();

}
