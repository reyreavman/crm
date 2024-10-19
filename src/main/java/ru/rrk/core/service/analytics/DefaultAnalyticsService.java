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
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.seller.FilteredSellerRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultAnalyticsService implements AnalyticsService {
    private final FilteredSellerRepository filteredSellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public SellerDTO findBestSeller(BestSellerFilters filters) {
        Seller bestSeller = null;
        if (filters.day() != null && filters.month() != null && filters.year() != null) {
            bestSeller = filteredSellerRepository.findBestSellerByDay(
                    LocalDate.of(filters.year(), filters.month(), filters.day())
            );
        } else if (filters.month() != null && filters.year() != null) {
            bestSeller = filteredSellerRepository.findBestSellerByMonth(
                    filters.month(), filters.year()
            );
        } else if (filters.quarter() != null && filters.year() != null) {
            bestSeller = filteredSellerRepository.findBestSellerByQuarter(
                    filters.quarter(), filters.year()
            );
        } else if (filters.year() != null) {
            bestSeller = filteredSellerRepository.findBestSellerByYear(
                    filters.year()
            );
        } else if (filters.day() == null && filters.month() == null && filters.quarter() == null && filters.year() == null) {
            bestSeller = filteredSellerRepository.findAllTimeBestSeller();
        }
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
