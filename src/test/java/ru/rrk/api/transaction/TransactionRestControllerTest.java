package ru.rrk.api.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TransactionRestControllerTest {
    static String URI = "/crm-api/v1/transactions/%d";
    @Autowired
    MockMvc mockMvc;

    @Test
    void getTransactionInfo() throws Exception {
        mockMvc.perform(get(URI.formatted(1)))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"id":1,"sellerId":10,"amount":30095.63,"paymentType":"CASH","transactionDate":"2023-12-19T00:00:00"}"""));
    }

    @Test
    void getTransactionInfo_returns404() throws Exception {
        mockMvc.perform(get(URI.formatted(131)))
                .andExpect(status().isNotFound());
    }
}
