package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.Address;
import charity.pejvak.coinbox.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    @Nullable
    private String email ;
    private String phoneNumber;
    private String nationalCode;
    private Role role;
}
