package charity.pejvak.coinbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    public void setCity(City city) {
        this.city = city;
    }
}
