package charity.pejvak.coinbox.controller;

import charity.pejvak.coinbox.componenet.TransactionResponse;
import charity.pejvak.coinbox.model.User;
import charity.pejvak.coinbox.model.enums.TransactionType;
import charity.pejvak.coinbox.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam(name = "type") TransactionType transactionType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(transactionService.getTransactions(user.getId(), transactionType));
    }
}
