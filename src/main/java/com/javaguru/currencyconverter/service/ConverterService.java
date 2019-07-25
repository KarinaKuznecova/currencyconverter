package com.javaguru.currencyconverter.service;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.repository.CurrencyRepository;
import com.javaguru.currencyconverter.repository.RatesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConverterService {

    private CurrencyRepository currencyRepository;
    private RatesRepository ratesRepository;

    public ConverterService(CurrencyRepository currencyRepository, RatesRepository ratesRepository) {
        this.currencyRepository = currencyRepository;
        this.ratesRepository = ratesRepository;
    }

    public ConvertationResult convertCurrency(Long primaryCurrencyId, Long secondaryCurrencyId, BigDecimal amount) {
        Currency currency = currencyRepository.findCurrencyById(primaryCurrencyId).orElseThrow(() ->
                new IllegalArgumentException("Currency pair with id " + primaryCurrencyId + " not found"));
        Rate rate = ratesRepository.findRatesById(secondaryCurrencyId).orElseThrow(() ->
                new IllegalArgumentException("Currency pair with id " + secondaryCurrencyId + " not found"));

        BigDecimal result = amount.multiply(rate.getRate()).setScale(4, RoundingMode.HALF_EVEN);
        BigDecimal fee = result.divide(BigDecimal.valueOf(100)).multiply(rate.getFee()).setScale(4, RoundingMode.HALF_EVEN);
        return new ConvertationResult(currency.getBase(), rate.getCurrencyName(), result, fee);
    }
}
