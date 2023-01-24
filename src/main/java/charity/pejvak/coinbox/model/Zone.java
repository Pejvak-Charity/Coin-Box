package charity.pejvak.coinbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "zone")
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false,nullable = false,updatable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "descriptioin")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    @JsonIgnore
    private City city;

    public Zone(String name, String description, City city) {
        this.name = name;
        this.description = description;
        this.city = city;
    }

    public Zone() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }


    @Deprecated()
    public void setCity(City city) {
        this.city = city;
    }
}
