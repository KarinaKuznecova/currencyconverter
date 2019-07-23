package com.javaguru.currencyconverter.controller;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.dto.CurrencyDTO;
import com.javaguru.currencyconverter.repository.RatesRepository;
import com.javaguru.currencyconverter.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/rates")
public class CurrencyRatesController {

    private CurrencyService currencyService;
    private RatesRepository ratesRepository;
    private RestTemplate restTemplate;

    public CurrencyRatesController(CurrencyService currencyService, RatesRepository ratesRepository, RestTemplate restTemplate) {
        this.currencyService = currencyService;
        this.ratesRepository = ratesRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public CurrencyDTO findRatesById(@PathVariable("id") Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        Set<Rate> rates = currency.getRates();
        HashMap<String, BigDecimal> rate = new HashMap<>();
        rates.forEach((Rate r) -> rate.put(r.getCurrencyName(), r.getRate()));
        return new CurrencyDTO(currency.getId(), currency.getBase(), currency.getDate(), rate);
    }

    @GetMapping("/actualRates")
    public CurrencyDTO getRatesFromApi() {
        CurrencyDTO currencyDTO = restTemplate.getForObject("https://api.exchangeratesapi.io/latest", CurrencyDTO.class);
        return currencyDTO;
    }

    @PostMapping(path = "/post", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Currency> create(@RequestBody CurrencyDTO currencyDTO) {
        Currency currency = new Currency();
        currency.setBase(currencyDTO.getBase());
        currency.setDate(currencyDTO.getDate());
        currencyService.createNewCurrency(currency);
        Set<Rate> rates = new HashSet<>();
        currencyDTO.getRates().forEach((String, BigDecimal) -> rates.add(new Rate(String, BigDecimal, currency.getId())));
        rates.forEach((Rate r) -> ratesRepository.createOrUpdateRate(r));
        currency.setRates(rates);
        currencyService.createNewCurrency(currency);
        return ResponseEntity.ok(currency);
    }
}
