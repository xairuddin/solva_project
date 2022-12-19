package kz.solva.repository;

import kz.solva.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT u FROM Transaction u where u.limit_exceeded = true")
    List<Transaction> findAllLimitedTransactions();

    @Query(value = "SELECT u FROM Transaction u where u.account_from = ?1")
    List<Transaction> findAllTransactionsByAccount(Long account_from);

    @Query(value = "SELECT u FROM Transaction u where u.account_from = ?1 and u.limit_exceeded = true")
    List<Transaction> findAllLimitedTransactionsByAccount(Long account_from);
}
