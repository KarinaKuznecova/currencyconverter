package com.javaguru.currencyconverter.service;

import com.javaguru.currencyconverter.domain.CurrencyPair;

import java.math.BigDecimal;

public class ConvertationResult {

    private final CurrencyPair currencyPair;
    private final BigDecimal resultBeforeFees;
    private final BigDecimal fee;
    private final BigDecimal finalResult;

    public ConvertationResult(BigDecimal resultBeforeFees, BigDecimal fee, CurrencyPair currencyPair) {
        this.resultBeforeFees = resultBeforeFees;
        this.fee = fee;
        this.currencyPair = currencyPair;
        finalResult = resultBeforeFees.subtract(fee);
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

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    @Override
    public String toString() {
        return currencyPair.getPrimaryCurrency() + " to " + currencyPair.getSecondaryCurrency() + " = " +
                resultBeforeFees + ". Fee = " + fee + ". Final result = " + finalResult;
    }
}
