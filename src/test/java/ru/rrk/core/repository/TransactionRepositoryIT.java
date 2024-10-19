package ru.rrk.core.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rrk.core.entity.transaction.Transaction;
import ru.rrk.testContainter.PostgresqlContainerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TransactionRepositoryIT {
    @ClassRule
    public static final PostgreSQLContainer postgres = PostgresqlContainerTest.getInstance();

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void findAllBySellerId_returnsTransactionsList() {
        Long sellerId = 3L;
        List<Long> expectedTransactionIDs = List.of(
                4L, 10L
        );

        List<Long> actualTransactionsIds = transactionRepository.findAllBySellerId(sellerId).stream()
                .map(Transaction::getId)
                .toList();

        assertEquals(expectedTransactionIDs, actualTransactionsIds);
    }
}
