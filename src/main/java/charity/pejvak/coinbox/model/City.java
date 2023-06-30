package charity.pejvak.coinbox.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "city")
@Table(name = "city")
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private final Set<Zone> zones = new HashSet<>();

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) && name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
