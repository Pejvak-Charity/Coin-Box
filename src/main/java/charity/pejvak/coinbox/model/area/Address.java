package charity.pejvak.coinbox.model.area;

import charity.pejvak.coinbox.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Address")
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;


    private String text;

    @ManyToOne()
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "zone_id",referencedColumnName = "id")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "id")
    private Province province;


    @Column(name = "zip_code",length = 11)
    private String zipCode;

}
