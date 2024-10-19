package ru.rrk.api.dto.filters;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record BestSellerFilters(
        @Positive
        @Max(31)
        Integer day,
        @Positive
        @Max(12)
        Integer month,
        @Positive
        @Max(4)
        Integer quarter,
        @Min(value = 1970)
        Integer year
) {
}
