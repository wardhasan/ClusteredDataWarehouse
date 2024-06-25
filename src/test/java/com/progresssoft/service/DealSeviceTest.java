package com.progresssoft.service;

import com.progresssoft.model.Deal;
import com.progresssoft.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceTest {

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealService dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDeal() {
        Deal deal = new Deal();
        deal.setdealId("12345");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(1000.0);

        when(dealRepository.save(deal)).thenReturn(deal);

        Deal savedDeal = dealService.saveDeal(deal);

        assertNotNull(savedDeal);
        assertEquals("12345", savedDeal.getdealId());
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    void testSaveDeals() {
        Deal deal = new Deal();
        deal.setdealId("12345");
        deal.setFromCurrencyIsoCode("USD");
        deal.setToCurrencyIsoCode("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(1000.0);

        List<Deal> deals = List.of(deal);
        when(dealRepository.saveAll(deals)).thenReturn(deals);

        List<Deal> savedDeals = dealService.saveDeals(deals);

        assertNotNull(savedDeals);
        assertEquals(1, savedDeals.size());
        verify(dealRepository, times(1)).saveAll(deals);
    }

    @Test
    void testDealExists() {
        when(dealRepository.existsById("12345")).thenReturn(true);

        boolean exists = dealService.dealExists("12345");

        assertTrue(exists);
        verify(dealRepository, times(1)).existsById("12345");
    }

    @Test
    void testDealDoesNotExist() {
        when(dealRepository.existsById("12345")).thenReturn(false);

        boolean exists = dealService.dealExists("12345");

        assertFalse(exists);
        verify(dealRepository, times(1)).existsById("12345");
    }
}