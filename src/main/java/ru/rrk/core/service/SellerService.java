package ru.rrk.core.service;

import org.springframework.data.domain.Page;
import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.PagedSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;

/*
Список всех продавцов
Инфо о конкретном продавце
Создать нового продавца
Обновить инфо о продавце
Удалить продавца
 */
public interface SellerService {
    PagedSellerDTO findAllSellers(int page, int pageSize);

    SellerDTO findSellerById(long id);

    SellerDTO createSeller(CreateSellerDTO createSellerDTO);

    SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO);

    void removeSeller(long id);
}
