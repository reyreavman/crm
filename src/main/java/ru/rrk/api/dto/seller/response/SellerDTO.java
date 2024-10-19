package ru.rrk.api.dto.seller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные продавца")
public record SellerDTO(
        @Schema(description = "Идентификатор пользователя")
        Long id,
        @Schema(description = "Имя продавца", example = "Иван Иванов")
        String name,
        @Schema(description = "Контактные данные", example = "+79999999999")
        String contactInfo
) {
}
