package ru.rrk.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.PagedSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.exception.ServiceException;
import ru.rrk.core.mapper.SellerMapper;
import ru.rrk.core.repository.seller.SellerRepository;

@Service
@RequiredArgsConstructor
//TODO: Раскидай @transactional
public class DefaultSellerService implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    @Override
    public PagedSellerDTO findAllSellers(int page, int pageSize) {
        Page<Seller> pagedSellers = sellerRepository.findAll(PageRequest.of(page, pageSize));
        return sellerMapper.mapToPagedSellerDTO(pagedSellers);
    }

    @Override
    public SellerDTO findSellerById(long id) {
        return sellerMapper.mapToSellerDTO(
                findById(id)
        );
    }

    @Override
    public SellerDTO createSeller(CreateSellerDTO createSellerDTO) {
        Seller savedSeller = sellerRepository.save(
                sellerMapper.mapToSeller(createSellerDTO)
        );
        return sellerMapper.mapToSellerDTO(savedSeller);
    }

    @Override
    public SellerDTO updateSeller(long id, UpdateSellerDTO updateSellerDTO) {
        Seller sellerWithUpdatedData = sellerMapper.updateSellerData(
                updateSellerDTO, findById(id)
        );
        return sellerMapper.mapToSellerDTO(sellerWithUpdatedData);
    }

    @Override
    public void removeSeller(long id) {
        sellerRepository.deleteById(id);
    }

    private Seller findById(long id) {
        return sellerRepository.findById(id).orElseThrow(
                () -> new ServiceException("User with id = %d not found".formatted(id))
        );
    }
}
