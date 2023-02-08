package charity.pejvak.coinbox.componenet;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String firstName;
    private String lastName;
    @Nullable
    private String email ;
    private String phoneNumber;
    private String nationalCode;
    private String role;
    private LocalDateTime signUpDate;
}
