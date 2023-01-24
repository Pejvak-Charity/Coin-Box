package charity.pejvak.coinbox.model;

import jakarta.persistence.*;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
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

    public Address(User user, String text, City city, Zone zone, Province province, String zipCode) {
        this.user = user;
        this.text = text;
        this.city = city;
        this.zone = zone;
        this.province = province;
        this.zipCode = zipCode;
    }

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
