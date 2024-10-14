package ru.rrk.core.service;

import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.PagedSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;

public interface SellerService {
    PagedSellerDTO findAllSellers(int page, int pageSize);

    SellerDTO findSellerById(long id);

    SellerDTO createSeller(CreateSellerDTO createSellerDTO);

    SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO);

    void removeSeller(long id);
}
