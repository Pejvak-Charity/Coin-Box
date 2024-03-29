package charity.pejvak.coinbox.model.user;


import charity.pejvak.coinbox.model.area.Address;
import charity.pejvak.coinbox.model.utility.Transaction;
import charity.pejvak.coinbox.model.enums.Role;
import charity.pejvak.coinbox.model.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "User")
@Table(name = "_User", uniqueConstraints = {
        @UniqueConstraint(name = "unique_nationalCode", columnNames =
                "national_code"),
        @UniqueConstraint(name = "unique_phoneNumber", columnNames =
                "phone_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "UserId", sequenceName = "UserId",
            allocationSize = 1)
    @GeneratedValue(generator = "UserId", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    @JsonIgnore
    private String username = "username";

    @Column(name = "password")
    @JsonIgnore
    private String password = "password";
    @Column(name = "email")
    private String email ;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "national_code")
    private String nationalCode;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
    }


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }


    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "sign-up-date", insertable = false)
    @CreatedDate
    private LocalDateTime signUpDate;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
