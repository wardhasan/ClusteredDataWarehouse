package com.progresssoft.service;

import com.progresssoft.model.Deal;
import com.progresssoft.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class DealService {

    private static final Logger logger = LoggerFactory.getLogger(DealService.class);

    @Autowired
    private DealRepository dealRepository;

    public Deal saveDeal(Deal deal) {
        try {
            return dealRepository.save(deal);
        } catch (Exception e) {
            logger.error("Error saving deal with ID {}: {}", deal.getdealId(), e.getMessage(), e);
            throw e;
        }
    }

    public List<Deal> saveDeals(List<Deal> deals) {
        try {
            return dealRepository.saveAll(deals);
        } catch (Exception e) {
            logger.error("Error saving deals: {}", e.getMessage(), e);
            throw e;
        }
    }

    public boolean dealExists(String dealId) {
        return dealRepository.existsById(dealId);
    }
}
