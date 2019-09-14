package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.service.ConvertationResult;
import com.javaguru.currencyconverter.service.ConverterService;
import com.javaguru.currencyconverter.service.CurrencyService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConvertMenu implements MenuItem {

    private final static String NAME = "Convert currency";
    private ConverterService converterService;
    private CurrencyService currencyService;
    private Reader reader = new Reader();

    public ConvertMenu(ConverterService converterService, CurrencyService currencyService) {
        this.converterService = converterService;
        this.currencyService = currencyService;
    }

    public void action() {
        Long primaryCurrencyId = 1L;
        Currency primaryCurrency = currencyService.getCurrencyById(primaryCurrencyId);
        printSecondaryCurrencies(primaryCurrency);
        Long secondaryCurrencyId = (long) reader.getUserInput("Enter currency id to convert from " + primaryCurrency.getBase());
        BigDecimal amount = BigDecimal.valueOf(reader.getUserInputDouble("Enter amount"));

        ConvertationResult result = converterService.convertCurrency(primaryCurrencyId, secondaryCurrencyId, amount);
        System.out.println(amount + " " + result);
    }

    private void printSecondaryCurrencies(Currency currency){
        currency.getRates().forEach((Rate r) -> System.out.println(r));
    }

    @Override
    public String toString() {
        return NAME;
    }
}
