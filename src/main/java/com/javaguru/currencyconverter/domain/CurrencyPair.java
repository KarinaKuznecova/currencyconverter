package com.javaguru.currencyconverter.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity(name = "CurrencyPair")
@Table
public class CurrencyPair {

    public final static BigDecimal DEFAULT_FEE = new BigDecimal(10);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "primaryCurrency")
    private String primaryCurrency;

    @Column(name = "secondaryCurrency")
    private String secondaryCurrency;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "fee")
    private BigDecimal fee;

    public CurrencyPair(String primaryCurrency, String secondaryCurrency, BigDecimal rate) {
        this.primaryCurrency = primaryCurrency;
        this.secondaryCurrency = secondaryCurrency;
        this.rate = rate;
        fee = DEFAULT_FEE;
    }

    public CurrencyPair() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrimaryCurrency() {
        return primaryCurrency;
    }

    public String getSecondaryCurrency() {
        return secondaryCurrency;
    }

    public BigDecimal getRate() {
        return rate.setScale(4, RoundingMode.HALF_EVEN);
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
        return id + ". " + primaryCurrency + "-" + secondaryCurrency + " "
                + getRate() + " + " + getFee() + "% fee";
    }
}
