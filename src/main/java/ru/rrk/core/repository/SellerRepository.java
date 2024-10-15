package ru.rrk.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.rrk.core.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>, PagingAndSortingRepository<Seller, Long> {
}
