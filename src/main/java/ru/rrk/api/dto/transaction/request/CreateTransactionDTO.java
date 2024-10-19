package ru.rrk.api.dto.transaction.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Данные необходимые для создания новой транзакции")
public record CreateTransactionDTO(
        @Schema(description = "ID продавца, к которому относится транзакция")
        @NotNull(message = "{crm.transactions.create.errors.sellerId_is_null}")
        @Positive(message = "{crm.transactions.create.errors.sellerId_is_negative}")
        Long sellerId,
        @Schema(description = "Сумма транзакции", exclusiveMinimum = true, minimum = "0")
        @NotNull(message = "{crm.transactions.create.errors.amount_is_null}")
        @Positive(message = "{crm.transactions.create.errors.amount_is_negative}")
        Float amount,
        @Schema(description = "Тип транзакции", allowableValues = {"cash", "card", "transfer"})
        @NotNull(message = "{crm.transactions.create.errors.paymentType_is_null}")
        String paymentType
) {
}
