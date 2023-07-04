package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.componenet.DigitalCoinBoxTransactionRequest;
import charity.pejvak.coinbox.componenet.DigitalCoinBoxTransactionResponse;
import charity.pejvak.coinbox.componenet.TransactionResponse;
import charity.pejvak.coinbox.model.utility.Transaction;
import charity.pejvak.coinbox.model.user.User;
import charity.pejvak.coinbox.model.enums.TransactionStatus;
import charity.pejvak.coinbox.model.enums.TransactionType;
import charity.pejvak.coinbox.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;


    private final UserService userService;

    public List<TransactionResponse> getTransactions(Long userid, TransactionType transactionType) {

        return repository.findByUser_IdAndTransactionType(userid, transactionType)
                .stream().map(transaction -> TransactionResponse
                        .builder()
                        .id(transaction.getId())
                        .referenceCode(transaction.getReferenceCode())
                        .dateTime(transaction.getDateTime())
                        .amount(transaction.getAmount())
                        .status(transaction.getStatus())
                        .transactionType(transaction.getTransactionType())
                        .userId(transaction.getUser().getId())
                        .description(transaction.getDescription())
                        .build()
                ).collect(Collectors.toList());

    }

    public DigitalCoinBoxTransactionResponse createDigitalCoinBoxRequest(Long userId, DigitalCoinBoxTransactionRequest digitalCoinBoxTransactionRequest) {

        User user = userService.getUser(userId);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.DigitalCoinBox)
                .status(TransactionStatus.INIT)
                .referenceCode(referenceCodeGenerator())
                .amount(digitalCoinBoxTransactionRequest.getAmount())
                .dateTime(LocalDateTime.now())
                .description(null)
                .user(user).build();


        transaction = repository.save(transaction);

        return DigitalCoinBoxTransactionResponse.builder()
                .gatewayURL(getGatewayURL(transaction.getReferenceCode()))
                .build();

    }

    public Long getTotal(Long userId, TransactionType transactionType) {
        return repository.findByUser_IdAndTransactionType(userId, transactionType).stream().mapToLong(Transaction::getAmount).sum();
    }

    private String referenceCodeGenerator() {
        //fixme
        return "ll;";
    }

    private String getGatewayURL(String refCode) {
        //fixme
        return "localHost:8080/pay/" + refCode;
    }

}
