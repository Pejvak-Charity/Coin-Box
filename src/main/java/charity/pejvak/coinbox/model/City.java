package charity.pejvak.coinbox.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "city")
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false,updatable = false,nullable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "province_id",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "city")
    private final Set<Zone> zones = new HashSet<>();

    public void addZone(Zone zone){
        zones.add(zone);
    }


    public City() {
    }


    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<Zone> getZones() {
        return zones;
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
