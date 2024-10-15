package ru.rrk.api.dto.seller;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные необходимые для обновления существующих данных продавца")
public record UpdateSellerDTO(
        @Schema(description = "Имя продавца", example = "Иван Иванов", nullable = true)
        String name,
        @Schema(description = "Контактные данные", example = "+79999999999", nullable = true)
        String contactInfo
) {
}
