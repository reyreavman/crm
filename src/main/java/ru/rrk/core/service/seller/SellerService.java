package ru.rrk.core.service.seller;

import org.springframework.data.domain.Pageable;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.seller.request.CreateSellerDTO;
import ru.rrk.api.dto.seller.request.UpdateSellerDTO;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.entity.Seller;

public interface SellerService {
    PagedData<SellerDTO> findPagedSellers(Pageable pageable);

    SellerDTO findSellerById(long id);

    SellerDTO createSeller(CreateSellerDTO createSellerDTO);

    SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO);

    void removeSeller(long id);

    Seller findSellerReferenceById(long id);

    boolean existsById(long id);
}
