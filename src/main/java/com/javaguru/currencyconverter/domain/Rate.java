package com.javaguru.currencyconverter.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "rates")
@Table
public class Rate {

    public final static BigDecimal DEFAULT_FEE = new BigDecimal(10);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currency")
    private String currencyName;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "fee")
    private BigDecimal fee;

    public Rate(String currencyName, BigDecimal rate, Long currencyId) {
        this.currencyName = currencyName;
        this.rate = rate;
        this.currencyId = currencyId;
        fee = DEFAULT_FEE;
    }

    public Rate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getFee() {
        if (fee == null) {
            fee = DEFAULT_FEE;
        }
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public void removeFee() {
        fee = DEFAULT_FEE;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", rate=" + rate +
                ", currencyId=" + currencyId +
                ", fee=" + fee +
                '}';
    }
}
