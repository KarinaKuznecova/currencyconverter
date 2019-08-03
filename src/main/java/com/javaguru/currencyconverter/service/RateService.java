package com.javaguru.currencyconverter.service;

import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.repository.RatesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RateService {

    private RatesRepository ratesRepository;

    public RateService(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public Rate createOrUpdateRate(Rate rate) {
        ratesRepository.createOrUpdateRate(rate);
        return rate;
    }

    public Rate getRateById(Long id) {
        return ratesRepository.findRatesById(id).orElseThrow(() ->
                new IllegalArgumentException("Rate with id " + id + " not found"));
    }

    public void setFee(Long rateId, BigDecimal fee) {
        ratesRepository.setFee(rateId, fee);
    }

    public BigDecimal checkFee(Long rateId) {
        return ratesRepository.getFee(rateId).orElseThrow(() ->
                new IllegalArgumentException("Fee for rate with id " + rateId + " not found"));
    }

    public void deleteFee(Long rateId) {
        ratesRepository.deleteFee(rateId);
    }
}
