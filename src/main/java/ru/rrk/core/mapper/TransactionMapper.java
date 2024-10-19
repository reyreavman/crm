package ru.rrk.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.rrk.api.dto.PagedData;
import ru.rrk.api.dto.transaction.request.CreateTransactionDTO;
import ru.rrk.api.dto.transaction.response.TransactionDTO;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.entity.transaction.Transaction;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "sellerId", source = "seller.id")
    TransactionDTO toTransactionDTO(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "seller", source = "seller")
    Transaction toTransaction(CreateTransactionDTO createTransactionDTO, Seller seller);

    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "pageNumber", source = "pageable.pageNumber")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "totalElements", source = "totalElements")
    @Mapping(target = "items", source = "content")
    PagedData<TransactionDTO> toPagedTransactionDTO(Page<Transaction> transactions);

    List<TransactionDTO> toTransactionDTOList(List<Transaction> transactionsList);
}
