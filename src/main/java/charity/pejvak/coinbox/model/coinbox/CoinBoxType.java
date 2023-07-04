package charity.pejvak.coinbox.model.coinbox;

import charity.pejvak.coinbox.model.utility.Image;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity(name = "coinBoxType")
@Table(name = "coin-box-type")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"images"})
public class CoinBoxType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private String size;

    @OneToMany(mappedBy = "coinBoxType")
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "type")
    private Set<CoinBox> coinBoxes = new HashSet<>();

    public void addImage(Image image){
        images.add(image);
    }

    public void addAllImages(Set<Image> images){
        this.images.addAll(images);
    }

}
