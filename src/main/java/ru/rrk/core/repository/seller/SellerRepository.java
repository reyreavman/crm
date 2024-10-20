package ru.rrk.core.repository.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rrk.core.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>, FilteredSellerRepository {
}
