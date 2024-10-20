package ru.rrk.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.rrk.core.entity.transaction.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findAllBySellerId(Long sellerId);

    Page<Transaction> findAllBySellerId(Long sellerId, Pageable pageable);
}
