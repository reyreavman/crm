package ru.rrk.api.dto.seller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Данные необходимые для создания нового продавца")
public record CreateSellerDTO(
        @Schema(description = "Имя продавца", example = "Иван Иванов")
        @NotNull(message = "{crm.sellers.create.errors.name_is_null}")
        @NotBlank(message = "{crm.sellers.create.errors.name_is_blank}")
        String name,
        @Schema(description = "Контактные данные", example = "+79999999999")
        @NotNull(message = "{crm.sellers.create.errors.contact_info_is_null}")
        @NotBlank(message = "{crm.sellers.create.errors.contact_info_is_blank}")
        String contactInfo
) {
}
