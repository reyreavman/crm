package ru.rrk.core.service.transaction;

import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.TransactionDTO;

import java.util.List;

public interface TransactionService {
    PagedData<TransactionDTO> findPagedTransactions(int page, int pageSize);

    List<TransactionDTO> findAllTransactions();

    TransactionDTO findTransactionById(long id);

    TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO);

    List<TransactionDTO> findAllUserTransactions(long userId);
}
