package ru.rrk.core.service.transaction;

import org.springframework.data.domain.Pageable;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.request.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.response.TransactionDTO;

public interface TransactionService {
    PagedData<TransactionDTO> findPagedTransactions(Long sellerId, Pageable pageable);

    TransactionDTO findTransactionById(long id);

    TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO);
}
