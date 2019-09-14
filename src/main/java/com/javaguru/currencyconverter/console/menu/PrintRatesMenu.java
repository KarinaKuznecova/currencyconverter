package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.CurrencyService;
import org.springframework.stereotype.Component;

@Component
public class PrintRatesMenu implements MenuItem {

    private final static String NAME = "Print currency rates";
    private CurrencyService currencyService;

    public PrintRatesMenu(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public void action() {
        currencyService.printRates();
    }

    @Override
    public String toString() {
        return NAME;
    }
}
