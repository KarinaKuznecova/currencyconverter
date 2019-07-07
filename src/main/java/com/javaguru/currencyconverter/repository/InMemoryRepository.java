package com.javaguru.currencyconverter.repository;

import com.javaguru.currencyconverter.domain.CurrencyPair;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
@Profile("inMemory")
public class InMemoryRepository implements CurrencyRepository {

    private Map<Long, CurrencyPair> currencyPairs;

    public InMemoryRepository(Map<Long, CurrencyPair> currencyPairs) {
        this.currencyPairs = currencyPairs;
    }

    public Optional<CurrencyPair> findPairById(Long id) {
        return Optional.ofNullable(currencyPairs.get(id));
    }

    public void setFee(Long id, BigDecimal fee) {
        currencyPairs.get(id).setFee(fee);
    }

    public void deleteFee(Long id) {
        currencyPairs.get(id).removeFee();
    }

    public Optional<BigDecimal> getFee(Long id){
        return Optional.ofNullable(currencyPairs.get(id).getFee());
    }

    public void printRates(){
        currencyPairs.values().stream().forEach(cp -> System.out.println(cp));
    }
}
