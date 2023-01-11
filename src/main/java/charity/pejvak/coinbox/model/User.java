package charity.pejvak.coinbox.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "_User",uniqueConstraints = {
        @UniqueConstraint(name = "unique_nationalCode",columnNames =
                "national_code"),
        @UniqueConstraint(name = "unique_phoneNumber",columnNames =
                "phone_number")
})
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "UserId",sequenceName = "UserId",
            allocationSize = 1)
    @GeneratedValue(generator = "UserId",strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_code")
    private String nationalCode;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
