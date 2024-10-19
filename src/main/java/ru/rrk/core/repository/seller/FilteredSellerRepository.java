package ru.rrk.core.repository.seller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.rrk.core.entity.Seller;

import java.time.LocalDate;

@Repository
public interface FilteredSellerRepository extends PagingAndSortingRepository<Seller, Long> {
    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            WHERE DATE(t.transaction_date) = :date
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            ORDER BY SUM(t.amount) DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Seller findBestSellerByDay(LocalDate date);

    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            WHERE EXTRACT(YEAR FROM DATE (t.transaction_date)) = :year
            AND EXTRACT(MONTH FROM DATE (t.transaction_date)) = :month
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            ORDER BY SUM(t.amount) DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Seller findBestSellerByMonth(int month, int year);

    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            WHERE EXTRACT(YEAR FROM DATE (t.transaction_date)) = :year
            AND EXTRACT(QUARTER FROM DATE (t.transaction_date)) = :quarter
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            ORDER BY SUM(t.amount) DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Seller findBestSellerByQuarter(int quarter, int year);

    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            WHERE EXTRACT(YEAR FROM DATE (t.transaction_date)) = :year
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            ORDER BY SUM(t.amount) DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Seller findBestSellerByYear(int year);

    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            ORDER BY SUM(t.amount) DESC
            LIMIT 1
            """,
            nativeQuery = true)
    Seller findAllTimeBestSeller();

    @Query(value = """
            SELECT s.id, s.name, s.contact_info, s.registration_date
            FROM Seller s
            JOIN Transaction t on s.id = t.seller_id
            WHERE (cast(:startDate as date) IS NULL OR DATE(t.transaction_date) >= :startDate)
            AND (cast(:endDate as date) IS NULL OR DATE(t.transaction_date) <= :endDate)
                        
            GROUP BY s.id, s.name, s.contact_info, s.registration_date
            HAVING SUM(t.amount) < :amount
            """,
            nativeQuery = true
    )
    Page<Seller> findSellersWithTransactionsSumLessThan(LocalDate startDate, LocalDate endDate, Float amount, Pageable pageable);


}
