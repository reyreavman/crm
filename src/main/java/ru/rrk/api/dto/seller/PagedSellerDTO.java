package ru.rrk.api.dto.seller;

import java.util.List;

public record PagedSellerDTO(
        Integer pageSize,
        Integer pageNumber,
        Integer totalPages,
        Integer totalElements,
        List<SellerDTO> items
) {
}
