package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.CurrencyPair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RatesConfiguration {

    @Bean
    public Map<Long, CurrencyPair> currencyPairs() {
        Map<Long, CurrencyPair> currencyPairs = new HashMap<>();
        currencyPairs.put(0L, new CurrencyPair("EUR", "USD", BigDecimal.valueOf(1.12)));
        currencyPairs.get(0L).setId(0L);
        currencyPairs.put(1L, new CurrencyPair("USD", "EUR", BigDecimal.valueOf(0.88)));
        currencyPairs.get(1L).setId(1L);
        currencyPairs.put(2L, new CurrencyPair("EUR", "GBP", BigDecimal.valueOf(0.89)));
        currencyPairs.get(2L).setId(2L);
        currencyPairs.put(3L, new CurrencyPair("GBP", "EUR", BigDecimal.valueOf(1.11)));
        currencyPairs.get(3L).setId(3L);
        currencyPairs.put(4L, new CurrencyPair("EUR", "AUD", BigDecimal.valueOf(1.60)));
        currencyPairs.get(4L).setId(4L);
        currencyPairs.put(5L, new CurrencyPair("AUD", "EUR", BigDecimal.valueOf(0.62)));
        currencyPairs.get(5L).setId(5L);
        return currencyPairs;
    }
}
