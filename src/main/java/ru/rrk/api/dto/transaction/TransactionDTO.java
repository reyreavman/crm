package ru.rrk.api.dto.transaction;

import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,
        Long sellerId,
        Float amount,
        String paymentType,
        LocalDateTime transactionDate
) {
}
