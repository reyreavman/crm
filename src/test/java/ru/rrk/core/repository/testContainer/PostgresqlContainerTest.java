package ru.rrk.core.repository.testContainer;

import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class PostgresqlContainerTest {
    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16")
            .withDatabaseName("crmTests")
            .withUsername("crmTests")
            .withPassword("crmTests");

    public static PostgreSQLContainer getInstance() {
        return postgres;
    }

    @AfterClass
    public static void stopContainer() {
        postgres.stop();
    }

    @Test
    public void test() {
        assertTrue(postgres.isRunning());
    }
}
