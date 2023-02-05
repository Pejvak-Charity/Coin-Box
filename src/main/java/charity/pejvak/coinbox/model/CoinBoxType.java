package charity.pejvak.coinbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity(name = "coinBoxType")
@Table(name = "coin-box-type")
public class CoinBoxType {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private String size;

    @OneToMany(mappedBy = "coinBoxType")
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "type")
    private Set<CoinBox> coinBoxes = new HashSet<>();

}
