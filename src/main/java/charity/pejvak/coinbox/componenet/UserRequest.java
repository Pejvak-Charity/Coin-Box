package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.enums.Role;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
