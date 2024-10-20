package ru.rrk.core.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rrk.core.entity.transaction.Transaction;
import ru.rrk.core.repository.data.DBData;
import ru.rrk.core.repository.testContainer.PostgresqlContainerTest;

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
        List<Transaction> expectedTransaction = List.of(
                DBData.getTransactionById(4L),
                DBData.getTransactionById(10L)
        );

        List<Transaction> actualTransactions = transactionRepository.findAllBySellerId(sellerId);

        assertEquals(expectedTransaction, actualTransactions);
    }
}
