package com.progresssoft.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
public class Deal {

    @Id
    @NotNull
    @Size(min = 1, max = 255)
    private String dealId;

    @NotBlank
    @Size(min = 3, max = 3)
    private String fromCurrencyIsoCode;

    @NotBlank
    @Size(min = 3, max = 3)
    private String toCurrencyIsoCode;

    @NotNull
    private LocalDateTime dealTimestamp;

    @NotNull
    private Double dealAmount;

    // Getters and setters

    public String getdealId() {
        return dealId;
    }

    public void setdealId(String dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrencyIsoCode() {
        return fromCurrencyIsoCode;
    }

    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
    }

    public String getToCurrencyIsoCode() {
        return toCurrencyIsoCode;
    }

    public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
        this.toCurrencyIsoCode = toCurrencyIsoCode;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }
}