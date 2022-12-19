package kz.solva.repository;

import kz.solva.model.exchange.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {
    @Query(value = "SELECT u FROM Meta u where u.symbol = ?1 order by u.datetime desc ")
    List<Meta> findExchangesBySymbol(String symbol);
}
