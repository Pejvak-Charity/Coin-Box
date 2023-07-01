package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.DigitalCoinBoxTransactionRequest;
import charity.pejvak.coinbox.componenet.DigitalCoinBoxTransactionResponse;
import charity.pejvak.coinbox.componenet.TransactionResponse;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.enums.TransactionType;
import charity.pejvak.coinbox.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    //todo add user id for request param and check authorization with logged in user.
    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam(name = "type") TransactionType transactionType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(transactionService.getTransactions(user.getId(), transactionType));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalDigitalCoinBox(@RequestParam(name = "type") TransactionType transactionType){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(transactionService.getTotal(user.getId(),transactionType));
    }


    @PostMapping("/digital-coin-box")
    public ResponseEntity<DigitalCoinBoxTransactionResponse> createDigitalCoinBoxTransaction(DigitalCoinBoxTransactionRequest digitalCoinBoxTransactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(transactionService.createDigitalCoinBoxRequest(user.getId(),digitalCoinBoxTransactionRequest));
    }
}
