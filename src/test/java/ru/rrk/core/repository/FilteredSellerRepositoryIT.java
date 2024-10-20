package ru.rrk.core.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rrk.core.entity.Seller;
import ru.rrk.core.repository.data.DBData;
import ru.rrk.core.repository.seller.FilteredSellerRepository;
import ru.rrk.core.repository.testContainer.PostgresqlContainerTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class FilteredSellerRepositoryIT {
    @ClassRule
    public static final PostgreSQLContainer postgres = PostgresqlContainerTest.getInstance();

    @Autowired
    FilteredSellerRepository filteredSellerRepository;

    @Test
    void findBestSellerByDay_returnsSeller() {
        LocalDate day = LocalDate.parse("2024-06-05");
        Seller expectedSeller = DBData.getSellerById(3L);

        Seller actualSeller = filteredSellerRepository.findBestSellerByDay(day);

        assertEquals(expectedSeller, actualSeller);
    }

    @Test
    void findBestSellerByDay_returnNull() {
        LocalDate day = LocalDate.parse("2005-06-05");

        Seller actualSeller = filteredSellerRepository.findBestSellerByDay(day);

        assertNull(actualSeller);
    }

    @Test
    void findBestSellerByMonth() {
        int month = 6;
        int year = 2024;
        Seller expectedSeller = DBData.getSellerById(7L);

        Seller actualSeller = filteredSellerRepository.findBestSellerByMonth(month, year);

        assertEquals(expectedSeller, actualSeller);
    }

    @Test
    void findBestSellerByMonth_returnsNull() {
        int month = 1;
        int year = 2000;

        Seller actualSeller = filteredSellerRepository.findBestSellerByMonth(month, year);

        assertNull(actualSeller);
    }

    @Test
    void findBestSellerByQuarter() {
        int quarter = 1;
        int year = 2024;
        Seller expectedSeller = DBData.getSellerById(1L);

        Seller actualSeller = filteredSellerRepository.findBestSellerByQuarter(quarter, year);

        assertEquals(expectedSeller, actualSeller);
    }

    @Test
    void findBestSellerByQuarter_returnsNull() {
        int quarter = 1;
        int year = 2000;

        Seller actualSeller = filteredSellerRepository.findBestSellerByQuarter(quarter, year);

        assertNull(actualSeller);
    }

    @Test
    void findBestSellerByYear() {
        int year = 2024;
        Seller expectedSeller = DBData.getSellerById(1L);

        Seller actualSeller = filteredSellerRepository.findBestSellerByYear(year);

        assertEquals(expectedSeller, actualSeller);
    }

    @Test
    void findBestSellerByYear_returnsNull() {
        int year = 2000;

        Seller actualSeller = filteredSellerRepository.findBestSellerByYear(year);

        assertNull(actualSeller);
    }

    @Test
    void findSellersWithTransactionsSumLessThan_startEndDatesSet() {
        LocalDate startDate = LocalDate.parse("2023-12-19");
        LocalDate endDate = LocalDate.parse("2025-01-01");
        Float amount = 65_000F;
        Pageable pageable = null;

        List<Seller> expectedSellers = List.of(
                DBData.getSellerById(8L),
                DBData.getSellerById(10L)
        );

        List<Seller> page = filteredSellerRepository.findSellersWithTransactionsSumLessThan(
                startDate, endDate, amount, pageable
        ).getContent();
    }

    @Test
    void findSellersWithTransactionsSumLessThan_startDateSet() {
        LocalDate startDate = LocalDate.parse("2023-12-19");
        Float amount = 65_000F;
        Pageable pageable = null;

        List<Seller> expectedSellers = List.of(
                DBData.getSellerById(8L),
                DBData.getSellerById(10L)
        );

        List<Seller> actualSellers = filteredSellerRepository.findSellersWithTransactionsSumLessThan(
                startDate, null, amount, pageable
        ).getContent();

        assertThat(expectedSellers)
                .containsExactlyInAnyOrderElementsOf(actualSellers);
    }

    @Test
    void findSellersWithTransactionsSumLessThan_endDateSet() {
        LocalDate endDate = LocalDate.parse("2025-01-01");
        Float amount = 65_000F;
        Pageable pageable = null;

        List<Seller> expectedSellers = List.of(
                DBData.getSellerById(8L),
                DBData.getSellerById(9L),
                DBData.getSellerById(10L)
        );

        List<Seller> actualSellers = filteredSellerRepository.findSellersWithTransactionsSumLessThan(
                null, endDate, amount, pageable
        ).getContent();

        assertThat(expectedSellers)
                .containsExactlyInAnyOrderElementsOf(actualSellers);
    }

    @Test
    void findSellersWithTransactionsSumLessThan() {
        Float amount = 65_000F;
        Pageable pageable = null;

        List<Seller> expectedSellers = List.of(
                DBData.getSellerById(8L),
                DBData.getSellerById(9L),
                DBData.getSellerById(10L)
        );

        List<Seller> actualSellers = filteredSellerRepository.findSellersWithTransactionsSumLessThan(
                null, null, amount, pageable
        ).getContent();

        assertThat(expectedSellers)
                .containsExactlyInAnyOrderElementsOf(actualSellers);
    }

}
