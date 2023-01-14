package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOTPRepository extends JpaRepository<UserOTP,Long> {
    Optional<UserOTP> findByOtpAndPhoneNumber(String otp, String phoneNumber);
}
