package ru.rrk.core.exception;

import lombok.Getter;
import ru.rrk.api.dto.filters.BestSellerFilters;

@Getter
public class FiltersException extends ServiceException {
    private final BestSellerFilters filters;
    public FiltersException(String message, BestSellerFilters filters) {
        super(message);
        this.filters = filters;
    }
}
