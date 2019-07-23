package com.javaguru.currencyconverter.dto;

import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyDTO {

    private Long id;
    private String base;
    private String date;
    private HashMap<String, BigDecimal> rates;

    public CurrencyDTO(Long id, String base, String date, HashMap<String, BigDecimal> rates) {
        this.id = id;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyDTO() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
