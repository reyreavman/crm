package ru.rrk.api.dto.seller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Данные необходимые для обновления существующих данных продавца")
public record UpdateSellerDTO(
        @Schema(description = "Имя продавца", example = "Иван Иванов", nullable = true)
        @NotBlank(message = "{crm.sellers.update.errors.name_is_blank}")
        String name,
        @Schema(description = "Контактные данные", example = "+79999999999", nullable = true)
        @NotBlank(message = "{crm.sellers.update.errors.contact_info_is_blank}")
        String contactInfo
) {
}
