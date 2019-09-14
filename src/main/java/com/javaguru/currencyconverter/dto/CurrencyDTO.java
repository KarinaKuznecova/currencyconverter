package com.javaguru.currencyconverter.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CurrencyDTO {

    private Long id;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;

    public CurrencyDTO(Long id, String base, String date, Map<String, BigDecimal> rates) {
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

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDTO that = (CurrencyDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(base, that.base) &&
                Objects.equals(date, that.date) &&
                Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, base, date, rates);
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
