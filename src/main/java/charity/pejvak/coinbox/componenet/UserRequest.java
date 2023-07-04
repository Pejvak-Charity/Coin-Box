package charity.pejvak.coinbox.componenet;

import charity.pejvak.coinbox.model.enums.Role;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Nullable
    private String email;

    //todo seperate address from user request plz !!!
    private Long provinceId;
    private Long cityId;

    @Nullable
    private String phoneNumber;
}
