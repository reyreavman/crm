package ru.rrk.api.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TransactionsRestControllerTest {
    static String URI = "/crm-api/v1/transactions";
    @Autowired
    MockMvc mockMvc;

    @Test
    void getPagedTransactions_withPageAndSizeParam() throws Exception {
        mockMvc.perform(get(URI)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":2,"pageNumber":0,"totalPages":10,"totalElements":20,"items":[{"id":1,"sellerId":10,"amount":30095.63,"paymentType":"CASH","transactionDate":"2023-12-19T00:00:00"},{"id":2,"sellerId":9,"amount":60619.406,"paymentType":"CASH","transactionDate":"2023-11-18T00:00:00"}]}"""));
    }

    @Test
    void getPagedTransactions_withSellerIdAndPageAndSizeParam() throws Exception {
        mockMvc.perform(get(URI)
                        .param("page", "0")
                        .param("size", "2")
                        .param("sellerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":2,"pageNumber":0,"totalPages":2,"totalElements":3,"items":[{"id":5,"sellerId":1,"amount":65341.02,"paymentType":"CASH","transactionDate":"2024-05-21T00:00:00"},{"id":7,"sellerId":1,"amount":64421.223,"paymentType":"CASH","transactionDate":"2024-01-22T00:00:00"}]}"""));
    }

    @Test
    void getPagedTransactions_withSellerId() throws Exception {
        mockMvc.perform(get(URI)
                        .param("sellerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":20,"pageNumber":0,"totalPages":1,"totalElements":3,"items":[{"id":5,"sellerId":1,"amount":65341.02,"paymentType":"CASH","transactionDate":"2024-05-21T00:00:00"},{"id":7,"sellerId":1,"amount":64421.223,"paymentType":"CASH","transactionDate":"2024-01-22T00:00:00"},{"id":8,"sellerId":1,"amount":1842.631,"paymentType":"CASH","transactionDate":"2024-03-14T00:00:00"}]}"""));
    }

    @Test
    void getPagedTransactions_withNonValidSellerId() throws Exception {
        mockMvc.perform(get(URI)
                        .param("sellerId", "-1"))
                .andExpect(status().isBadRequest());
    }

//    //TODO СНИМИ КОММЕНТ
//    @Test
//    void createTransactions() throws Exception {
//        mockMvc.perform(post(URI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {"sellerId": "1","amount": "1000","paymentType":"CARD"}
//                                """))
//                .andExpect(status().isOk());
//    }

    @Test
    void createSeller_returns400_nonValidAmountProvided() throws Exception {
        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"sellerId": "1","amount": "-1000","paymentType":"CARD"}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createSeller_returns400_nonExistsSellerIdProvided() throws Exception {
        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"sellerId": "-1","amount": "-1000","paymentType":"CARD"}
                                """))
                .andExpect(status().isBadRequest());
    }
}
