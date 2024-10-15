package ru.rrk.core.service.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.TransactionDTO;
import ru.rrk.core.entity.transaction.Transaction;
import ru.rrk.core.exception.service.ServiceException;
import ru.rrk.core.mapper.TransactionMapper;
import ru.rrk.core.repository.SellerRepository;
import ru.rrk.core.repository.TransactionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
//TODO: Раскидай @transactional
public class DefaultTransactionService implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public PagedData<TransactionDTO> findPagedTransactions(int page, int pageSize) {
        Page<Transaction> pagedTransactions = transactionRepository.findAll(PageRequest.of(page, pageSize));
        return transactionMapper.toPagedTransactionDTO(pagedTransactions);
    }

    @Override
    public List<TransactionDTO> findAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toTransactionDTO)
                .toList();
    }

    @Override
    public TransactionDTO findTransactionById(long id) {
        return transactionMapper.toTransactionDTO(
                findById(id)
        );
    }

    @Override
    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction savedTransaction = transactionRepository.save(
                transactionMapper.toTransaction(
                        createTransactionDTO, sellerRepository.getReferenceById(createTransactionDTO.sellerId())
                )
        );
        return transactionMapper.toTransactionDTO(savedTransaction);
    }

    @Override
    public List<TransactionDTO> findAllUserTransactions(long sellerId) {
        return transactionMapper.toTransactionDTOList(
                transactionRepository.findAllBySellerId(sellerId)
        );
    }

    private Transaction findById(long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ServiceException("Transaction with id %d not found".formatted(id))
        );
    }
}
