package ru.rrk.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rrk.api.dto.filters.BestSellerFilters;
import ru.rrk.api.dto.filters.MinTransactionsFilters;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.exception.FiltersException;
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.seller.FilteredSellerRepository;
import ru.rrk.core.service.analytics.DefaultAnalyticsService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DefaultAnalyticsServiceTest {
    @Mock
    FilteredSellerRepository filteredSellerRepository;
    @Mock
    SellerMapper sellerMapper;
    @InjectMocks
    DefaultAnalyticsService analyticsService;

    @Test
    void findBestSeller_throwsFilterException() {
        BestSellerFilters filters = new BestSellerFilters(31, 11, 3, 2024);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSeller_throwsFilterException_dateTimeExceptionCaught() {
        BestSellerFilters filters = new BestSellerFilters(31, 11, null, 2024);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSellerByDay() {
        BestSellerFilters filters = new BestSellerFilters(31, 12, null, 2024);
        LocalDate date = LocalDate.of(filters.year(), filters.month(), filters.day());

        doReturn(null)
                .when(filteredSellerRepository).findBestSellerByDay(date);
        doReturn(null)
                .when(sellerMapper).toSellerDTO(null);

        SellerDTO actualBestSeller = analyticsService.findBestSeller(filters);

        assertNull(actualBestSeller);
        assertDoesNotThrow(() -> analyticsService.findBestSeller(filters));

    }

    @Test
    void findBestSellerByMonth() {
        BestSellerFilters filters = new BestSellerFilters(null, 12, null, 2024);

        doReturn(null)
                .when(filteredSellerRepository).findBestSellerByMonth(filters.month(), filters.year());
        doReturn(null)
                .when(sellerMapper).toSellerDTO(null);

        SellerDTO actualBestSeller = analyticsService.findBestSeller(filters);

        assertNull(actualBestSeller);
        assertDoesNotThrow(() -> analyticsService.findBestSeller(filters));
    }

    @Test
    void findBestSellerByQuarter() {
        BestSellerFilters filters = new BestSellerFilters(null, null, 1, 2024);

        doReturn(null)
                .when(filteredSellerRepository).findBestSellerByQuarter(filters.quarter(), filters.year());
        doReturn(null)
                .when(sellerMapper).toSellerDTO(null);

        SellerDTO actualBestSeller = analyticsService.findBestSeller(filters);

        assertNull(actualBestSeller);
        assertDoesNotThrow(() -> analyticsService.findBestSeller(filters));
    }

    @Test
    void findBestSellerByYear() {
        BestSellerFilters filters = new BestSellerFilters(null, null, null, 2024);

        doReturn(null)
                .when(filteredSellerRepository).findBestSellerByYear(filters.year());
        doReturn(null)
                .when(sellerMapper).toSellerDTO(null);

        SellerDTO actualBestSeller = analyticsService.findBestSeller(filters);

        assertNull(actualBestSeller);
        assertDoesNotThrow(() -> analyticsService.findBestSeller(filters));
    }

    @Test
    void findBestSeller_throwsFiltersException_dayMonthProvided() {
        BestSellerFilters filters = new BestSellerFilters(30, 12, null, null);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSeller_throwsFiltersException_dayQuarterProvided() {
        BestSellerFilters filters = new BestSellerFilters(30, null, 1, null);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSeller_throwsFiltersException_dayYearProvided() {
        BestSellerFilters filters = new BestSellerFilters(30, null, null, 1980);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSeller_throwsFiltersException_dayQuarterYearProvided() {
        BestSellerFilters filters = new BestSellerFilters(30, null, 1, 1980);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }

    @Test
    void findBestSeller_throwsFiltersException_monthQuarterProvided() {
        BestSellerFilters filters = new BestSellerFilters(null, 10, 1, null);

        assertThrows(FiltersException.class, () -> analyticsService.findBestSeller(filters), "Передан невалидный набор фильтров");
    }
}
