package ru.rrk.api.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Данные транзакции")
public record TransactionDTO(
        @Schema(description = "Идентификатор транзакции")
        Long id,
        @Schema(description = "Идентификатор пользователя, к которому относится транзакция")
        Long sellerId,
        @Schema(description = "Сумма транзакции", exclusiveMinimum = true, minimum = "0")
        Float amount,
        @Schema(description = "Тип транзакции", allowableValues = {"cash", "card", "transfer"})
        String paymentType,
        @Schema(description = "Время совершения транзакции")
        LocalDateTime transactionDate
) {
}
