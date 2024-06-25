package com.progresssoft.controller;

import com.progresssoft.model.Deal;
import com.progresssoft.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DealControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dealController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // Ensure UTF-8 encoding
                .build();
    }

    @Test
    void testImportDeals() throws Exception {
        Deal deal = new Deal();
        deal.setdealId("12345");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(1000.0);

        when(dealService.dealExists("12345")).thenReturn(false);
        when(dealService.saveDeal(any(Deal.class))).thenReturn(deal);

        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"dealId\":\"12345\",\"fromCurrencyIsoCode\":\"USD\",\"toCurrencyIsoCode\":\"EUR\",\"dealTimestamp\":\"2023-01-01T10:00:00\",\"dealAmount\":1000.0}]"))
                .andExpect(status().isOk());

        verify(dealService, times(1)).dealExists("12345");
        verify(dealService, times(1)).saveDeal(any(Deal.class));
    }

    @Test
    void testImportDeals_Duplicate() throws Exception {
        when(dealService.dealExists("12345")).thenReturn(true);

        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"dealId\":\"12345\",\"fromCurrencyIsoCode\":\"USD\",\"toCurrencyIsoCode\":\"EUR\",\"dealTimestamp\":\"2023-01-01T10:00:00\",\"dealAmount\":1000.0}]"))
                .andExpect(status().isConflict());

        verify(dealService, times(1)).dealExists("12345");
        verify(dealService, times(0)).saveDeal(any(Deal.class));
    }

    @Test
    void testImportDeals_InvalidInput() throws Exception {
        mockMvc.perform(post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"dealId\":\"12345\",\"toCurrencyIsoCode\":\"EUR\",\"dealTimestamp\":\"2023-01-01T10:00:00\",\"dealAmount\":1000.0}]"))
                .andExpect(status().isBadRequest());
    }
}