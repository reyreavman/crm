package ru.rrk.api.seller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SellerRestControllerTest {
    static String URI = "/crm-api/v1/sellers/%d";
    @Autowired
    MockMvc mockMvc;

    @Test
    void getSellerInfo() throws Exception {
        mockMvc.perform(get(URI.formatted(1)))
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"id":1,"name":"Tessa Porcher","contactInfo":"+86 387 685 9952"}"""));
    }

    @Test
    void getSellerInfo_returns404() throws Exception {
        mockMvc.perform(get(URI.formatted(100)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getSellerInfo_returns400_idEqualsToZero() throws Exception {
        mockMvc.perform(get(URI.formatted(0)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getSellerInfo_returns404_negativeId() throws Exception {
        mockMvc.perform(get(URI.formatted(-100)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateSellerInfo() throws Exception {
        mockMvc.perform(
                        patch(URI.formatted(1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"name": "Ivan", "contactInfo":"vinvin@example.com"}""")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("""
                        {"id":1,"name":"Ivan","contactInfo":"vinvin@example.com"}"""));
    }

    @Test
    void updateSellerInfo_returns400_nonValidDataProvided() throws Exception {
        mockMvc.perform(
                        patch(URI.formatted(1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"name": " ", "contactInfo":"vinvin@example.com"}""")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateSellerInfo_returns404() throws Exception {
        mockMvc.perform(
                        patch(URI.formatted(100))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {"name": " ", "contactInfo":"vinvin@example.com"}""")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteSeller() throws Exception {
        mockMvc.perform(delete(URI.formatted(1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSeller_returns400() throws Exception {
        mockMvc.perform(delete(URI.formatted(0)))
                .andExpect(status().isBadRequest());
    }
}
