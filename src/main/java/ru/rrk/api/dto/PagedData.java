package ru.rrk.api.dto;

import java.util.List;

public record PagedData<T>(
        Integer pageSize,
        Integer pageNumber,
        Integer totalPages,
        Integer totalElements,
        List<T> items
) {
}
