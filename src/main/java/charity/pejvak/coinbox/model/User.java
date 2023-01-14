package charity.pejvak.coinbox.model;


import charity.pejvak.coinbox.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity(name = "User")
@Table(name = "_User",uniqueConstraints = {
        @UniqueConstraint(name = "unique_nationalCode",columnNames =
                "national_code"),
        @UniqueConstraint(name = "unique_phoneNumber",columnNames =
                "phone_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "UserId",sequenceName = "UserId",
            allocationSize = 1)
    @GeneratedValue(generator = "UserId",strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username = "username";

    @Column(name = "password")
    private String password = "password";
    @Column(name = "email")
    private String email = "email";

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "national_code")
    private String nationalCode;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<UserOTP> userOTPS = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
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
