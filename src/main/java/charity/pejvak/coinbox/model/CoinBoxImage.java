package charity.pejvak.coinbox.model;


import jakarta.persistence.*;

@Entity(name = "coinBoxImage")
@Table(name = "coin-box-image")
public class CoinBoxImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private String size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coinbox_id",referencedColumnName = "id")
    private CoinBox coinBox;


}
