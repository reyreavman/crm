package ru.rrk.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.seller.CreateSellerDTO;
import ru.rrk.api.dto.seller.SellerDTO;
import ru.rrk.api.dto.seller.UpdateSellerDTO;
import ru.rrk.core.entity.Seller;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface SellerMapper {
    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    SellerDTO toSellerDTO(Seller seller);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    Seller toSeller(CreateSellerDTO createSellerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    Seller updateSellerData(UpdateSellerDTO updateSellerDTO, @MappingTarget Seller seller);

    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "pageNumber", source = "pageable.pageNumber")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "items", source = "content")
    PagedData<SellerDTO> toPagedSellerDTO(Page<Seller> sellers);
}
