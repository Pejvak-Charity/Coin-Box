package charity.pejvak.coinbox.repository;

import charity.pejvak.coinbox.model.utility.Transaction;
import charity.pejvak.coinbox.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByUser_IdAndTransactionType(Long userId, TransactionType transactionType);
}
