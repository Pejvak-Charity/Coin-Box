package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.CoinBoxUserRequestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class CoinBoxUserRequestController {

    @GetMapping("/{userId}/coin-box-requests")
    public ResponseEntity<Map<String, Object>> getCoinBoxResuests(
            @PathVariable long userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "50") int pageSize
    ) {
        return null;
    }

    @GetMapping("/{userId}/coin-box-requests/{requestId}")
    public ResponseEntity<?> getCoinBoxResuests(
            @PathVariable long userId,
            @PathVariable long requestId) {
        return null;
    }

    @PostMapping("/{userId}/coin-box-requests")
    public ResponseEntity addCoinBoxRequest(
            @PathVariable long userId,
            @RequestBody CoinBoxUserRequestRequest request) {
        return null;
    }

    @PutMapping("/{userId}/coin-box-requests")
    public ResponseEntity updateStatus(@PathVariable long userId) {
        return null;
    }

    @GetMapping("/{userId}/coin-box-requests/{requestId}/history")
    public ResponseEntity getRequestHistory(
            @PathVariable long userId,
            @PathVariable long requestId
    ) {
        return null;
    }
}
