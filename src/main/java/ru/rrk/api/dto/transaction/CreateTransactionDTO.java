package ru.rrk.api.dto.transaction;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные необходимые для создания новой транзакции")
public record CreateTransactionDTO(
        @Schema(description = "ID продавца, к которому относится транзакция")
        Long sellerId,
        @Schema(description = "Сумма транзакции", exclusiveMinimum = true, minimum = "0")
        Float amount,
        @Schema(description = "Тип транзакции", allowableValues = {"cash", "card", "transfer"})
        String paymentType
) {
}
