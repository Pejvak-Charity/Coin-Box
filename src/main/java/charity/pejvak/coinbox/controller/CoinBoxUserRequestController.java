package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CoinBoxUserRequestRequest;
import charity.pejvak.coinbox.componenet.NewCoinBoxUserRequest;
import charity.pejvak.coinbox.componenet.CoinBoxUserRequestResponse;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.service.CoinBoxUserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxUserRequestController {

    private final CoinBoxUserRequestService coinBoxUserRequestService;



    @PostMapping("/coin-box-requests/new-coin-box")
    public ResponseEntity<CoinBoxUserRequestResponse> addCoinBoxRequest(
            @RequestBody NewCoinBoxUserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(coinBoxUserRequestService.newCoinBoxUser(user.getId(), request));
    }

    @PostMapping("/coin-box-requests/counting-request")
    public ResponseEntity<CoinBoxUserRequestResponse> count(
            @RequestBody CoinBoxUserRequestRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(coinBoxUserRequestService.countingRequest(user.getId(), request));
    }

    @PostMapping("/coin-box-requests/returning-request")
    public ResponseEntity<CoinBoxUserRequestResponse> returning(
            @RequestBody CoinBoxUserRequestRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(coinBoxUserRequestService.returningRequest(user.getId(), request));
    }

}
