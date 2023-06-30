package charity.pejvak.coinbox.service;

import charity.pejvak.coinbox.componenet.TransactionResponse;
import charity.pejvak.coinbox.model.enums.TransactionType;
import charity.pejvak.coinbox.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

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
}
