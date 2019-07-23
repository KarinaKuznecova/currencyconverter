package com.javaguru.currencyconverter.service;

import java.math.BigDecimal;

public class ConvertationResult {

    private final String primaryCurrency;
    private final String secondaruCurrency;
    private final BigDecimal resultBeforeFees;
    private final BigDecimal fee;
    private final BigDecimal finalResult;

    public ConvertationResult(String primaryCurrency, String secondaruCurrency, BigDecimal resultBeforeFees, BigDecimal fee) {
        this.primaryCurrency = primaryCurrency;
        this.secondaruCurrency = secondaruCurrency;
        this.resultBeforeFees = resultBeforeFees;
        this.fee = fee;
        finalResult = resultBeforeFees.subtract(fee);
    }

    public String getPrimaryCurrency() {
        return primaryCurrency;
    }

    public String getSecondaruCurrency() {
        return secondaruCurrency;
    }

    public BigDecimal getResultBeforeFees() {
        return resultBeforeFees;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public BigDecimal getFinalResult() {
        return finalResult;
    }

    @Override
    public String toString() {
        return primaryCurrency + " to " + secondaruCurrency + " = " + resultBeforeFees +
                ". Fee: " + fee + ". Final result: " + finalResult;
    }
}
