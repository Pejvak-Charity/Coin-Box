package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.AuthenticationRequest;
import charity.pejvak.coinbox.componenet.AuthenticationResponse;
import charity.pejvak.coinbox.componenet.OTPRequest;
import charity.pejvak.coinbox.componenet.OTPResponse;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.service.JWTService;
import charity.pejvak.coinbox.service.UserOTPService;
import charity.pejvak.coinbox.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/login")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LoginController {
    private final UserOTPService userOtpService;

    private final UserService userService;

    private final JWTService jwtService;


    @GetMapping("")
    public ResponseEntity<String> getTestMessage(){
        return ResponseEntity.ok("Let's explore Coin Box Project !");
    }
    @PostMapping("/send-otp")
    public ResponseEntity<OTPResponse> getOTP(@RequestBody OTPRequest otpRequest, HttpServletRequest httpRequest) {
      userOtpService.getOTP(otpRequest.getPhoneNumber(), httpRequest.getRemoteAddr());
        return ResponseEntity.ok(OTPResponse.builder().message("OTP sent successfully !").build());
    }

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        userOtpService.checkAndDeleteOTP(authRequest.getOtp(), authRequest.getPhoneNumber());
        User user = userService.addOrGetUserByPhoneNumber(authRequest.getPhoneNumber());
        AuthenticationResponse response = AuthenticationResponse.builder()
                                                                .token(jwtService.generateToken(user))
                                                                .userId(user.getId())
                .role(user.getRole()).build();
        return ResponseEntity.ok(response);
    }
}
