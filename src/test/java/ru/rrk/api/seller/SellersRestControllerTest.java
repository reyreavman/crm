package ru.rrk.api.seller;

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
public class SellersRestControllerTest {
    static String URI = "/crm-api/v1/sellers";
    @Autowired
    MockMvc mockMvc;

    @Test
    void getPagedSellers() throws Exception {
        mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":20,"pageNumber":0,"totalPages":1,"totalElements":10,"items":[{"id":1,"name":"Tessa Porcher","contactInfo":"+86 387 685 9952"},{"id":2,"name":"Athena Ludlow","contactInfo":"+7 120 534 9963"},{"id":3,"name":"Bo Marrow","contactInfo":"+7 224 997 3458"},{"id":4,"name":"Drud Kersting","contactInfo":"+81 617 892 4377"},{"id":5,"name":"Belva Ossenna","contactInfo":"+30 974 494 2736"},{"id":6,"name":"Vale Beals","contactInfo":"+358 853 247 8176"},{"id":7,"name":"Lonna Campes","contactInfo":"+380 600 733 0551"},{"id":8,"name":"Carol Casbon","contactInfo":"+66 776 437 5750"},{"id":9,"name":"Walden Confort","contactInfo":"+385 704 939 9075"},{"id":10,"name":"Linnie Winterscale","contactInfo":"+503 605 502 1032"}]}"""));
    }

    @Test
    void getPagedSellers_withPageAndSizeParam() throws Exception {
        mockMvc.perform(get(URI)
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":2,"pageNumber":0,"totalPages":5,"totalElements":10,"items":[{"id":1,"name":"Tessa Porcher","contactInfo":"+86 387 685 9952"},{"id":2,"name":"Athena Ludlow","contactInfo":"+7 120 534 9963"}]}"""));
    }

    @Test
    void getPagedSellers_withMinTransactionsSum() throws Exception {
        mockMvc.perform(get(URI)
                        .param("minTransactionsSum", "1000"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":20,"pageNumber":0,"totalPages":0,"totalElements":0,"items":null}"""));
    }

    @Test
    void getPagedSellers_withMinTransactionsSumAndStartDate() throws Exception {
        mockMvc.perform(get(URI)
                        .param("minTransactionsSum", "100000")
                        .param("startDate", "2022-10-23"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":20,"pageNumber":0,"totalPages":1,"totalElements":5,"items":[{"id":3,"name":"Bo Marrow","contactInfo":"+7 224 997 3458"},{"id":4,"name":"Drud Kersting","contactInfo":"+81 617 892 4377"},{"id":8,"name":"Carol Casbon","contactInfo":"+66 776 437 5750"},{"id":9,"name":"Walden Confort","contactInfo":"+385 704 939 9075"},{"id":10,"name":"Linnie Winterscale","contactInfo":"+503 605 502 1032"}]}"""));
    }

    @Test
    void getPagedSellers_withMinTransactionsSumAndStartDateAndEndDate() throws Exception {
        mockMvc.perform(get(URI)
                        .param("minTransactionsSum", "100000")
                        .param("startDate", "2022-10-23")
                        .param("startDate", "2025-10-23"))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"pageSize":20,"pageNumber":0,"totalPages":1,"totalElements":5,"items":[{"id":3,"name":"Bo Marrow","contactInfo":"+7 224 997 3458"},{"id":4,"name":"Drud Kersting","contactInfo":"+81 617 892 4377"},{"id":8,"name":"Carol Casbon","contactInfo":"+66 776 437 5750"},{"id":9,"name":"Walden Confort","contactInfo":"+385 704 939 9075"},{"id":10,"name":"Linnie Winterscale","contactInfo":"+503 605 502 1032"}]}"""));
    }

    @Test
    void createSeller_returns400_nonValidDataProvided() throws Exception {
        mockMvc.perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name": "   ", "contactInfo":"ivan@example.com"}
                                """))
                .andExpect(status().isBadRequest());

    }

    @Test
    void getBestSeller() throws Exception {
        mockMvc.perform(get(URI.concat("/best")))
                .andExpect(status().isOk());

    }

    @Test
    void getBestSeller_filtersProvided() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("day","15")
                        .param("month", "12")
                        .param("year","2024"))
                .andExpect(status().isOk());
    }

    @Test
    void getBestSeller_returns400_nonValidDayFilterProvided() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("day","35")
                        .param("month", "12")
                        .param("year","2024"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getBestSeller_withMonthAndYearFilters() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("month","10")
                        .param("year","2024"))
                .andExpect(status().isOk());
    }

    @Test
    void getBestSeller_withYearFilter() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("year","2024"))
                .andExpect(status().isOk());
    }

    @Test
    void getBestSeller_withQuarterAndYearFilter() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("year","2024")
                        .param("quarter","4"))
                .andExpect(status().isOk());
    }

    @Test
    void getBestSeller_returns400_nonValidDayAndYearFiltersProvided() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("day","10")
                        .param("year","2024"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getBestSeller_returns400_nonValidDayAndQuarterFiltersProvided() throws Exception {
        mockMvc.perform(get(URI.concat("/best"))
                        .param("day","10")
                        .param("quarter","4"))
                .andExpect(status().isBadRequest());
    }
}
