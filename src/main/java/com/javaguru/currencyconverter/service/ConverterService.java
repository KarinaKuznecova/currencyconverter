package com.javaguru.currencyconverter.service;

import com.javaguru.currencyconverter.domain.CurrencyPair;
import com.javaguru.currencyconverter.repository.CurrencyRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ConverterService {

    private CurrencyRepository currencyRepository;

    public ConverterService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ConvertationResult convertCurrency(Long id, BigDecimal amount) {
        CurrencyPair pair = currencyRepository.findPairById(id).orElseThrow(() ->
                new IllegalArgumentException("Currency pair with id " + id + " not found"));
        BigDecimal result = amount.multiply(pair.getRate()).setScale(4, RoundingMode.HALF_EVEN);
        BigDecimal fee = result.divide(BigDecimal.valueOf(100)).multiply(pair.getFee()).setScale(4, RoundingMode.HALF_EVEN);
        return new ConvertationResult(result, fee, pair);
    }

    public void printRates() {
        currencyRepository.printRates();
    }

    public void setFee(Long id, BigDecimal fee) {
        currencyRepository.setFee(id, fee);
    }

    public BigDecimal checkFee(Long id) {
        BigDecimal fee = currencyRepository.getFee(id).orElseThrow(() ->
                new IllegalArgumentException("Fee for currency pair with id " + id + " not found"));
        return fee;
    }

    public void deleteFee(Long id) {
        currencyRepository.deleteFee(id);
    }
}
