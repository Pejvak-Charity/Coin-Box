package charity.pejvak.coinbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;

    @Column(name = "image_path")
    private String path;

    @ManyToOne()
    @JoinColumn(name = "coin-box-type-id", referencedColumnName = "id")
    private CoinBoxType coinBoxType;

}
