package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.exception.ExpiredOTPException;
import charity.pejvak.coinbox.exception.NoSuchUserOTPExistsException;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.UserOTP;
import charity.pejvak.coinbox.model.enums.Role;
import charity.pejvak.coinbox.repository.UserOTPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserOTPService {

    private final UserOTPRepository userOtpRepository;
    private final UserService userService;

    private final static int OTP_EXP_MINUTES = 3;

    public UserOTP getOTP(String phoneNumber, String ip) {
        User user = userService.addOrGetUserByPhoneNumber(phoneNumber);
        if (user == null) {
            user = new User();
            user.setRole(Role.USER);
            user.setPhoneNumber(phoneNumber);
            user.setUsername(phoneNumber);
            user = userService.addUser(user);
        }

        UserOTP userOTP = UserOTP.builder().otp(generateCode(4)).createDate(new Date()).phoneNumber(user.getPhoneNumber()).ip(ip).user(user).build();

        // todo send otp

        return userOtpRepository.saveAndFlush(userOTP);

    }

    public void checkAndDeleteOTP(String otp, String phoneNumber) {
        UserOTP userOTP = userOtpRepository.findByOtpAndPhoneNumber(otp, phoneNumber).orElseThrow(() -> {
            throw new NoSuchUserOTPExistsException();
        });

        if (userOTP.getCreateDate().before(new Date(System.currentTimeMillis() - OTP_EXP_MINUTES * 60 * 1000)))

            throw new ExpiredOTPException();

        userOtpRepository.delete(userOTP);
    }

    private String generateCode(int digitCount) {
        StringBuilder stringBuilder = new StringBuilder(digitCount);
        Random random = new Random(1L);
        for (int i = 0; i < digitCount; i++) {
            stringBuilder.append(random.nextInt());
        }
        //return stringBuilder.toString();
        return "1234";
    }
}
