package ru.rrk.api.dto.transaction;

public record CreateTransactionDTO(
        Long sellerId,
        Float amount,
        String paymentType
) {
}
