package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.ConverterService;
import org.springframework.stereotype.Component;

@Component
public class PrintRatesMenu implements MenuItem {

    private final static String NAME = "Print currency rates";
    private ConverterService converterService;

    public PrintRatesMenu(ConverterService converterService) {
        this.converterService = converterService;
    }

    public void action() {
        converterService.printRates();
    }

    @Override
    public String toString() {
        return NAME;
    }
}
