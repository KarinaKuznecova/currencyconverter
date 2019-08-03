package com.javaguru.currencyconverter.mapper;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class Converter {


    public Currency convert(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();
        currency.setBase(currencyDTO.getBase());
        currency.setDate(currencyDTO.getDate());
        currency.setId(currencyDTO.getId());
        Set<Rate> rates = new HashSet<>();
        currencyDTO.getRates().forEach((name, rate) -> rates.add(new Rate(name, rate, currency.getId())));
        currency.setRates(rates);
        return currency;
    }

    public CurrencyDTO convert(Currency currency) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setBase(currency.getBase());
        currencyDTO.setDate(currency.getDate());
        currencyDTO.setId(currency.getId());
        Map<String, BigDecimal> rates = new HashMap<>();
        currency.getRates().forEach((rate) -> rates.put(rate.getCurrencyName(), rate.getRate()));
        currencyDTO.setRates(rates);
        return currencyDTO;
    }
}
