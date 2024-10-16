package ru.rrk.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Данные запроса с пагинацией")
public record PagedData<T>(
        @Schema(description = "Размер страницы")
        Integer pageSize,
        @Schema(description = "Номер страницы")
        Integer pageNumber,
        @Schema(description = "Общее количество страниц")
        Integer totalPages,
        @Schema(description = "Общее количество элементов")
        Integer totalElements,
        @Schema(description = "Список элементов")
        List<T> items
) {
}
