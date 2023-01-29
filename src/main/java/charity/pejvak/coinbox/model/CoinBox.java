package charity.pejvak.coinbox.model;


import charity.pejvak.coinbox.model.enums.CoinBoxType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "coinBox")
@Table(name = "coin-box")
public class CoinBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CoinBoxType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @OneToMany(mappedBy = "coinBox")
    @JsonIgnore
    private Set<CoinBoxUserRequest> requests = new HashSet<>();

    @OneToMany(mappedBy = "coinBox")
    @JsonIgnore
    private Set<CoinBoxImage> coinBoxImages = new HashSet<>();

    public CoinBox() {
    }

    public CoinBox(CoinBoxType type, String name, String size, Integer status) {
        this.type = type;
        this.name = name;
        this.size = size;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CoinBoxType getType() {
        return type;
    }

    public void setType(CoinBoxType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<CoinBoxUserRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<CoinBoxUserRequest> requests) {
        this.requests = requests;
    }

    public Set<CoinBoxImage> getCoinBoxImages() {
        return coinBoxImages;
    }

    public void addImage(CoinBoxImage coinBoxImages) {
        this.coinBoxImages.add(coinBoxImages);
    }
}
