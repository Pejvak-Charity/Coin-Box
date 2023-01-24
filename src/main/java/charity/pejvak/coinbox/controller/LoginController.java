package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.AuthenticationRequest;
import charity.pejvak.coinbox.componenet.AuthenticationResponse;
import charity.pejvak.coinbox.componenet.OTPRequest;
import charity.pejvak.coinbox.componenet.OTPResponse;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.UserOTP;
import charity.pejvak.coinbox.service.JWTService;
import charity.pejvak.coinbox.service.UserOTPService;
import charity.pejvak.coinbox.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class LoginController {
    private final UserOTPService userOtpService;

    private final UserService userService;

    private final JWTService jwtService;


    @GetMapping("/login")
    public ResponseEntity<String> getTestMessage(){
        return ResponseEntity.ok("hi ali");
    }
    @PostMapping("/send-otp")
    public ResponseEntity<OTPResponse> getOTP(@RequestBody OTPRequest otpRequest, HttpServletRequest httpRequest) {
        UserOTP userOTP = userOtpService.getOTP(otpRequest.getPhoneNumber(), httpRequest.getRemoteAddr());
        return ResponseEntity.ok(OTPResponse.builder().message("OTP sent successfully !").build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        userOtpService.checkOTP(authRequest.getOtp(), authRequest.getPhoneNumber());
        User user = userService.getUserByPhoneNumber(authRequest.getPhoneNumber());
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtService.generateToken(user)).build());
    }
}
