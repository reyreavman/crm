package ru.rrk.core.service.analytics;

import org.springframework.data.domain.Pageable;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.filters.BestSellerFilters;
import ru.rrk.api.dto.filters.MinTransactionsFilters;
import ru.rrk.api.dto.seller.response.SellerDTO;

public interface AnalyticsService {
    SellerDTO findBestSeller(BestSellerFilters filters);

    PagedData<SellerDTO> findSellersWithTransactionSumLessThan(Pageable pageable, MinTransactionsFilters filters);
}
