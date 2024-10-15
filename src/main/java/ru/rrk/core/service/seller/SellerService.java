package ru.rrk.core.service.seller;

import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;

import java.util.List;

public interface SellerService {
    PagedData<SellerDTO> findPagedSellers(int page, int pageSize);

    List<SellerDTO> findAllSellers();

    SellerDTO findSellerById(long id);

    SellerDTO createSeller(CreateSellerDTO createSellerDTO);

    SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO);

    void removeSeller(long id);
}
