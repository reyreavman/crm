package ru.rrk.api.dto.error;


import lombok.Getter;
import ru.rrk.api.dto.filters.BestSellerFilters;

@Getter
public class FiltersErrorDTO extends ServiceErrorDTO{
    private final BestSellerFilters filters;
    public FiltersErrorDTO(String message, BestSellerFilters filters) {
        super(message);
        this.filters = filters;
    }
}
