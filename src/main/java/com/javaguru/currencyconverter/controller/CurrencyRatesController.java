package com.javaguru.currencyconverter.controller;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.dto.CurrencyDTO;
import com.javaguru.currencyconverter.mapper.Converter;
import com.javaguru.currencyconverter.service.CurrencyService;
import com.javaguru.currencyconverter.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rates")
public class CurrencyRatesController {

    private CurrencyService currencyService;
    private RateService rateService;
    private Converter converter;

    public CurrencyRatesController(CurrencyService currencyService, RateService rateService, Converter converter) {
        this.currencyService = currencyService;
        this.rateService = rateService;
        this.converter = converter;
    }

    @GetMapping(params = {"id"})
    public CurrencyDTO findRatesById(@RequestParam("id") Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        return converter.convert(currency);
    }

    @GetMapping(params = {"name"})
    public Currency getByName(@RequestParam("name") String name) {
        return currencyService.getByName(name).orElseThrow(IllegalArgumentException::new);
    }

    @PostMapping
    public ResponseEntity<Currency> create(@RequestBody CurrencyDTO currencyDTO) {
        Currency currency = converter.convert(currencyDTO);
        currency.getRates().forEach(rate -> rateService.createOrUpdateRate(rate));
        currencyService.createOrUpdateCurrency(currency);
        return ResponseEntity.ok(currency);
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<Currency> update(@RequestParam("id") Long id, CurrencyDTO currencyDTO) {
        Currency currency = converter.convert(currencyDTO);
        currency.getRates().forEach(rate -> rateService.createOrUpdateRate(rate));
        currencyService.createOrUpdateCurrency(currency);
        return ResponseEntity.ok(currency);
    }

    @DeleteMapping(params = {"id"})
    public void delete(@RequestParam("id") Long id) {
        currencyService.deleteCurrency(id);
    }
}
