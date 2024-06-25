package com.progresssoft.controller;

import com.progresssoft.model.Deal;
import com.progresssoft.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/deals")
public class DealController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    @Autowired
    private DealService dealService;

    @PostMapping
    public ResponseEntity<?> importDeals(@RequestBody @Valid List<Deal> deals) {
        for (Deal deal : deals) {
            if (dealService.dealExists(deal.getdealId())) {
                logger.warn("Duplicate deal ID: {}", deal.getdealId());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate deal ID: " + deal.getdealId());
            }
            dealService.saveDeal(deal);
            logger.info("Saved deal with ID: {}", deal.getdealId());
        }
        return ResponseEntity.ok("Deals imported successfully.");
    }
}