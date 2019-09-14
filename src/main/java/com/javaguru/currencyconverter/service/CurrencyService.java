package com.javaguru.currencyconverter.service;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.dto.CurrencyDTO;
import com.javaguru.currencyconverter.mapper.Converter;
import com.javaguru.currencyconverter.repository.CurrencyNotFoundException;
import com.javaguru.currencyconverter.repository.CurrencyRepository;
import com.javaguru.currencyconverter.repository.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CurrencyService {

    private RestTemplate restTemplate;
    private CurrencyRepository currencyRepository;
    private RatesRepository ratesRepository;
    private Converter converter;

    @Autowired
    public CurrencyService(RestTemplate restTemplate, CurrencyRepository currencyRepository, RatesRepository ratesRepository,
                           Converter converter) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
        this.ratesRepository = ratesRepository;
        this.converter = converter;
        initRates();
    }

    private void initRates() {
        CurrencyDTO currencyDTO = getRates();
        Currency currency = converter.convert(currencyDTO);
        currency.getRates().forEach(rate -> ratesRepository.createOrUpdateRate(rate));
        createOrUpdateCurrency(currency);
    }

    public CurrencyDTO getRates() {
        return restTemplate.getForObject("https://api.exchangeratesapi.io/latest", CurrencyDTO.class);
    }

    public Currency createOrUpdateCurrency(Currency currency) {
        currencyRepository.saveOrUpdateCurrency(currency);
        return currency;
    }

    public Currency getCurrencyById(Long id) {
        return currencyRepository.findCurrencyById(id).orElseThrow(() ->
                new CurrencyNotFoundException("Currency with id " + id + " not found"));
    }

    public void printRates() {
        currencyRepository.printRates();
    }

    public void addRateToCurrency(Long currencyId, Rate rate) {
        Currency currency = getCurrencyById(currencyId);
        currency.addRate(rate);
        currencyRepository.saveOrUpdateCurrency(currency);
    }

    public void removeRateForCurrency(Long currencyId, Rate rate) {
        Currency currency = getCurrencyById(currencyId);
        currency.removeRate(rate);
        currencyRepository.saveOrUpdateCurrency(currency);
    }

    public void deleteCurrency(Long id) {
        Currency currency = getCurrencyById(id);
        currencyRepository.removeCurrency(currency);
    }

    public Optional<Currency> getByName(String name) {
        return currencyRepository.getByName(name);
    }
}
