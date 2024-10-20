package ru.rrk.core.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.request.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.response.TransactionDTO;
import ru.rrk.core.entity.transaction.Transaction;
import ru.rrk.core.exception.ServiceException;
import ru.rrk.core.mapper.TransactionMapper;
import ru.rrk.core.repository.TransactionRepository;
import ru.rrk.core.service.seller.SellerService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final SellerService sellerService;

    @Override
    public PagedData<TransactionDTO> findPagedTransactions(Long sellerId, Pageable pageable) {
        Page<Transaction> pagedTransaction;
        if (Objects.nonNull(sellerId)) {
            pagedTransaction = transactionRepository.findAllBySellerId(sellerId, pageable);
        } else {
            pagedTransaction = transactionRepository.findAll(pageable);
        }
        return transactionMapper.toPagedTransactionDTO(pagedTransaction);
    }

    @Override
    public TransactionDTO findTransactionById(long id) {
        return transactionMapper.toTransactionDTO(
                findById(id)
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        if (!sellerService.existsById(createTransactionDTO.sellerId())) {
            throw new ServiceException("Пользователь с id = %d не найден".formatted(createTransactionDTO.sellerId()));
        }
        Transaction savedTransaction = transactionRepository.save(
                transactionMapper.toTransaction(
                        createTransactionDTO, sellerService.findSellerReferenceById(createTransactionDTO.sellerId())
                )
        );
        return transactionMapper.toTransactionDTO(savedTransaction);
    }

    private Transaction findById(long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ServiceException("Транзакция с id = %d не найдена".formatted(id))
        );
    }
}
