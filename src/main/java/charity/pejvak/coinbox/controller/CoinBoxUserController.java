package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CoinBoxUserResponse;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.service.CoinBoxUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxUserController {

    private final CoinBoxUserService coinBoxUserService;


    @GetMapping("/my-coin-boxes")
    public ResponseEntity<List<CoinBoxUserResponse>> addCoinBoxRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(coinBoxUserService.getCoinBoxUser(user.getId()));
    }

}
