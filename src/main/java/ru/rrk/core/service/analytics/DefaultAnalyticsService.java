package ru.rrk.core.service.analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.filters.BestSellerFilters;
import ru.rrk.api.dto.filters.MinTransactionsFilters;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.exception.FiltersException;
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.seller.FilteredSellerRepository;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultAnalyticsService implements AnalyticsService {
    private final FilteredSellerRepository filteredSellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public SellerDTO findBestSeller(BestSellerFilters filters) {
        Seller bestSeller;
        if (
                filters.day() != null &&
                filters.month() != null &&
                filters.quarter() == null &&
                filters.year() != null
        ) {
            LocalDate date;
            try {
                date = LocalDate.of(filters.year(), filters.month(), filters.day());
            } catch (DateTimeException e) {
                throw new FiltersException("Передан невалидный набор фильтров", filters);
            }
            bestSeller = filteredSellerRepository.findBestSellerByDay(date);
        } else if (
                filters.day() == null &&
                filters.month() != null &&
                filters.quarter() == null &&
                filters.year() != null
        ) {
            bestSeller = filteredSellerRepository.findBestSellerByMonth(
                    filters.month(), filters.year()
            );
        } else if (
                filters.day() == null &&
                filters.month() == null &&
                filters.quarter() != null &&
                filters.year() != null
        ) {
            bestSeller = filteredSellerRepository.findBestSellerByQuarter(
                    filters.quarter(), filters.year()
            );
        } else if (
                filters.day() == null &&
                filters.month() == null &&
                filters.quarter() == null &&
                filters.year() != null
        ) {
            bestSeller = filteredSellerRepository.findBestSellerByYear(filters.year());
        } else if (
                filters.day() == null &&
                filters.month() == null &&
                filters.quarter() == null &&
                filters.year() == null
        ) {
            bestSeller = filteredSellerRepository.findAllTimeBestSeller();
        } else
            throw new FiltersException("Передан невалидный набор фильтров", filters);
        return sellerMapper.toSellerDTO(bestSeller);
    }

    @Override
    public PagedData<SellerDTO> findSellersWithTransactionSumLessThan(Pageable pageable,
                                                                      MinTransactionsFilters filters) {
        Page<Seller> sellersWithTransactionsSumLessThan = filteredSellerRepository.findSellersWithTransactionsSumLessThan(
                filters.startDate(), filters.endDate(), filters.minTransactionsSum(), pageable
        );
        return sellerMapper.toPagedSellerDTO(sellersWithTransactionsSumLessThan);
    }
}
