package kz.solva.repository;

import kz.solva.model.Limit;
import kz.solva.model.exchange.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    @Query(value = "SELECT l FROM Limit l join l.categoryOwner c where c.id= ?1 and l.account_from=?2 order by l.id desc ")
    List<Limit> findLimitByCategoryOwnerIdAndAccount_from(Long id, Long account_from);

    @Query(value = "SELECT l FROM Limit l join l.categoryOwner c where l.account_from= ?1 and c.category_name=?2 order by l.id desc ")
    List<Limit> findLimitByAccount_fromAndExpense_category(Long account_from, String expense_category);

    @Query(value = "SELECT l from Limit l where l.account_from = ?1")
    List<Limit> findLimitByAccount_from(Long account_from);
}
