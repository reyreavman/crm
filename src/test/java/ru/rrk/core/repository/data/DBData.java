package ru.rrk.core.repository.data;

import ru.rrk.core.entity.Seller;
import ru.rrk.core.entity.transaction.PaymentType;
import ru.rrk.core.entity.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class DBData {
    private static final List<Seller> preparedSellers = List.of(
            new Seller(1L, "Tessa Porcher", "+86 387 685 9952", LocalDateTime.parse("2024-07-08T00:00:00")),
            new Seller(2L, "Athena Ludlow", "+7 120 534 9963", LocalDateTime.parse("2024-06-02T00:00:00")),
            new Seller(3L, "Bo Marrow", "+7 224 997 3458", LocalDateTime.parse("2024-06-08T00:00:00")),
            new Seller(4L, "Drud Kersting", "+81 617 892 4377", LocalDateTime.parse("2023-12-16T00:00:00")),
            new Seller(5L, "Belva Ossenna", "+30 974 494 2736", LocalDateTime.parse("2022-10-05T00:00:00")),
            new Seller(6L, "Vale Beals", "+358 853 247 8176", LocalDateTime.parse("2023-12-23T00:00:00")),
            new Seller(7L, "Lonna Campes", "+380 600 733 0551", LocalDateTime.parse("2024-08-14T00:00:00")),
            new Seller(8L, "Carol Casbon", "+66 776 437 5750", LocalDateTime.parse("2023-07-02T00:00:00")),
            new Seller(9L, "Walden Confort", "+385 704 939 9075", LocalDateTime.parse("2024-06-14T00:00:00")),
            new Seller(10L, "Linnie Winterscale", "+503 605 502 1032", LocalDateTime.parse("2023-05-18T00:00:00"))
    );
    private static final List<Transaction> preparedTransactions = List.of(
            new Transaction(1L, preparedSellers.get(9), Float.valueOf("30095.63"), PaymentType.CASH, LocalDateTime.parse("2023-12-19T00:00:00")),
            new Transaction(2L, preparedSellers.get(8), Float.valueOf("60619.406"), PaymentType.CASH, LocalDateTime.parse("2023-11-18T00:00:00")),
            new Transaction(3L, preparedSellers.get(3), Float.valueOf("39980.142"), PaymentType.CASH, LocalDateTime.parse("2023-12-25T00:00:00")),
            new Transaction(4L, preparedSellers.get(2), Float.valueOf("52486.088"), PaymentType.CASH, LocalDateTime.parse("2024-06-30T00:00:00")),
            new Transaction(5L, preparedSellers.get(0), Float.valueOf("65341.018"), PaymentType.CASH, LocalDateTime.parse("2024-05-21T00:00:00")),
            new Transaction(6L, preparedSellers.get(1), Float.valueOf("17888.079"), PaymentType.CASH, LocalDateTime.parse("2024-03-18T00:00:00")),
            new Transaction(7L, preparedSellers.get(0), Float.valueOf("64421.221"), PaymentType.CASH, LocalDateTime.parse("2024-01-22T00:00:00")),
            new Transaction(8L, preparedSellers.get(0), Float.valueOf("1842.631"), PaymentType.CASH, LocalDateTime.parse("2024-03-14T00:00:00")),
            new Transaction(9L, preparedSellers.get(1), Float.valueOf("89071.844"), PaymentType.CASH, LocalDateTime.parse("2024-09-03T00:00:00")),
            new Transaction(10L, preparedSellers.get(2), Float.valueOf("26195.918"), PaymentType.CASH, LocalDateTime.parse("2024-06-05T00:00:00")),
            new Transaction(11L, preparedSellers.get(3), Float.valueOf("19991.863"), PaymentType.CASH, LocalDateTime.parse("2024-06-05T00:00:00")),
            new Transaction(12L, preparedSellers.get(4), Float.valueOf("37677.819"), PaymentType.CASH, LocalDateTime.parse("2024-01-23T00:00:00")),
            new Transaction(13L, preparedSellers.get(5), Float.valueOf("92359.745"), PaymentType.CASH, LocalDateTime.parse("2023-12-21T00:00:00")),
            new Transaction(14L, preparedSellers.get(6), Float.valueOf("70002.407"), PaymentType.CASH, LocalDateTime.parse("2024-06-20T00:00:00")),
            new Transaction(15L, preparedSellers.get(5), Float.valueOf("98526.971"), PaymentType.CASH, LocalDateTime.parse("2024-09-25T00:00:00")),
            new Transaction(16L, preparedSellers.get(4), Float.valueOf("93753.674"), PaymentType.CASH, LocalDateTime.parse("2024-10-18T00:00:00")),
            new Transaction(17L, preparedSellers.get(3), Float.valueOf("14263.351"), PaymentType.CASH, LocalDateTime.parse("2023-12-31T00:00:00")),
            new Transaction(18L, preparedSellers.get(7), Float.valueOf("4570.136"), PaymentType.CASH, LocalDateTime.parse("2024-07-13T00:00:00")),
            new Transaction(19L, preparedSellers.get(7), Float.valueOf("32513.584"), PaymentType.CASH, LocalDateTime.parse("2024-05-20T00:00:00")),
            new Transaction(20L, preparedSellers.get(9), Float.valueOf("3879.705"), PaymentType.CASH, LocalDateTime.parse("2024-10-02T00:00:00"))
    );

    public static Seller getSellerById(Long id) {
        return preparedSellers.stream()
                .filter(seller -> seller.getId().equals(id))
                .findFirst().orElseThrow();
    }

    public static Transaction getTransactionById(Long id) {
        return preparedTransactions.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst().orElseThrow();
    }
}
