package ru.rrk.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rrk.api.dto.transaction.request.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.response.TransactionDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.entity.transaction.PaymentType;
import ru.rrk.core.entity.transaction.Transaction;
import ru.rrk.core.exception.ServiceException;
import ru.rrk.core.mapper.TransactionMapper;
import ru.rrk.core.repository.TransactionRepository;
import ru.rrk.core.service.seller.SellerService;
import ru.rrk.core.service.transaction.DefaultTransactionService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    TransactionMapper transactionMapper;
    @Mock
    SellerService sellerService;

    @InjectMocks
    DefaultTransactionService transactionService;

    @Test
    void findTransactionById() {
        long id = 10;
        Transaction foundTransaction = new Transaction(10L, null, 1000F, PaymentType.CASH, LocalDateTime.now());
        TransactionDTO transactionDTO = new TransactionDTO(foundTransaction.getId(), null, foundTransaction.getAmount(), foundTransaction.getPaymentType(), foundTransaction.getTransactionDate());

        doReturn(Optional.of(foundTransaction))
                .when(transactionRepository).findById(id);
        doReturn(transactionDTO)
                .when(transactionMapper).toTransactionDTO(foundTransaction);

        TransactionDTO actualTransactionDTO = transactionService.findTransactionById(id);

        assertEquals(transactionDTO, actualTransactionDTO);
    }

    @Test
    void findSellerById_throwsServiceException() {
        long id = 100;

        doThrow(ServiceException.class)
                .when(transactionRepository).findById(id);

        assertThrows(ServiceException.class, () -> transactionService.findTransactionById(id));
        verifyNoInteractions(transactionMapper);
    }

    @Test
    void createTransaction() {
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(10L, 1000F, PaymentType.CASH);
        Seller seller = new Seller(10L, "Иван", "ivan@example.com", LocalDateTime.now());
        Transaction mappedTransaction = new Transaction(null, seller, createTransactionDTO.amount(), createTransactionDTO.paymentType(), LocalDateTime.now());
        Transaction savedTransaction = new Transaction(1L, seller, createTransactionDTO.amount(), createTransactionDTO.paymentType(), mappedTransaction.getTransactionDate());
        TransactionDTO transactionDTO = new TransactionDTO(savedTransaction.getId(), savedTransaction.getSeller().getId(), savedTransaction.getAmount(), savedTransaction.getPaymentType(), savedTransaction.getTransactionDate());

        doReturn(true)
                .when(sellerService).existsById(createTransactionDTO.sellerId());
        doReturn(seller)
                .when(sellerService).findSellerReferenceById(createTransactionDTO.sellerId());
        doReturn(mappedTransaction)
                .when(transactionMapper).toTransaction(createTransactionDTO, seller);
        doReturn(savedTransaction)
                .when(transactionRepository).save(mappedTransaction);
        doReturn(transactionDTO)
                .when(transactionMapper).toTransactionDTO(savedTransaction);

        TransactionDTO actualTransactionDTO = transactionService.createTransaction(createTransactionDTO);

        assertEquals(transactionDTO, actualTransactionDTO);
    }

    @Test
    void createTransaction_throwsServiceException() {
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(10L, 1000F, PaymentType.CASH);

        doReturn(false)
                .when(sellerService).existsById(createTransactionDTO.sellerId());

        assertThrows(ServiceException.class, () -> transactionService.createTransaction(createTransactionDTO));
        verify(sellerService).existsById(createTransactionDTO.sellerId());
        verifyNoMoreInteractions(sellerService);
        verifyNoInteractions(transactionMapper);
    }
}
