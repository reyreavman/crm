package ru.rrk.core.service.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.seller.request.CreateSellerDTO;
import ru.rrk.api.dto.seller.request.UpdateSellerDTO;
import ru.rrk.api.dto.seller.response.SellerDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.exception.service.ServiceException;
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.seller.SellerRepository;

@Service
@RequiredArgsConstructor
public class DefaultSellerService implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public PagedData<SellerDTO> findPagedSellers(Pageable pageable) {
        Page<Seller> pagedSellers = sellerRepository.findAll(pageable);
        return sellerMapper.toPagedSellerDTO(pagedSellers);
    }

    @Override
    public SellerDTO findSellerById(long id) {
        return sellerMapper.toSellerDTO(
                findById(id)
        );
    }

    @Override
    public SellerDTO createSeller(CreateSellerDTO createSellerDTO) {
        Seller savedSeller = sellerRepository.save(
                sellerMapper.toSeller(createSellerDTO)
        );
        return sellerMapper.toSellerDTO(savedSeller);
    }

    @Override
    @Transactional
    public SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO) {
        Seller sellerWithUpdatedData = sellerMapper.updateSellerData(
                updateSellerDTO, findById(id)
        );
        return sellerMapper.toSellerDTO(sellerWithUpdatedData);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void removeSeller(long id) {
        sellerRepository.deleteById(id);
    }

    @Override
    public Seller findSellerReferenceById(long id) {
        return sellerRepository.getReferenceById(id);
    }

    @Override
    public boolean existsById(long id) {
        return sellerRepository.existsById(id);
    }

    private Seller findById(long id) {
        return sellerRepository.findById(id).orElseThrow(
                () -> new ServiceException("Пользователь с id = %d не найден".formatted(id))
        );
    }
}
