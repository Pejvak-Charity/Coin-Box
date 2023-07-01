package charity.pejvak.coinbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "CoinBoxUser")
@Table(name = "coin_box_user")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CoinBoxUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    private Long userId;

    private Long coinBoxId;

}
