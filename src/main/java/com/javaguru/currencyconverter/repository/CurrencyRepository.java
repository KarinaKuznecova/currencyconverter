package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.CurrencyPair;

import java.math.BigDecimal;
import java.util.Optional;

public interface CurrencyRepository {

    Optional<CurrencyPair> findPairById(Long id);

    void setFee(Long id, BigDecimal fee);

    void deleteFee(Long id);

    Optional<BigDecimal> getFee(Long id);

    void printRates();
}
