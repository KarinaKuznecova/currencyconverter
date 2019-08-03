package com.javaguru.currencyconverter.controller;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.dto.CurrencyDTO;
import com.javaguru.currencyconverter.mapper.Converter;
import com.javaguru.currencyconverter.service.CurrencyService;
import com.javaguru.currencyconverter.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/rates")
public class CurrencyRatesController {

    private CurrencyService currencyService;
    private RateService rateService;
    private RestTemplate restTemplate;
    private Converter converter;

    public CurrencyRatesController(CurrencyService currencyService, RateService rateService, RestTemplate restTemplate, Converter converter) {
        this.currencyService = currencyService;
        this.rateService = rateService;
        this.restTemplate = restTemplate;
        this.converter = converter;
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

    @GetMapping("/byName/{name}")
    public Currency getByName(@PathVariable("name") String name) {
        return currencyService.getByName(name).orElseThrow(() -> new IllegalArgumentException());
    }

    @PostMapping
    public ResponseEntity<Currency> create(@RequestBody CurrencyDTO currencyDTO) {
        Currency currency = converter.convert(currencyDTO);
        currency.getRates().forEach((rate) -> rateService.createOrUpdateRate(rate));
        currencyService.createOrUpdateCurrency(currency);
        return ResponseEntity.ok(currency);
    }

    @PutMapping
    public ResponseEntity<Currency> update(@RequestBody CurrencyDTO currencyDTO) {
        Currency currency = converter.convert(currencyDTO);
        currency.getRates().forEach((rate) -> rateService.createOrUpdateRate(rate));
        currencyService.createOrUpdateCurrency(currency);
        return ResponseEntity.ok(currency);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        currencyService.deleteCurrency(id);
    }
}
