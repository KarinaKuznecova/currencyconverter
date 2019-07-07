package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.ConvertationResult;
import com.javaguru.currencyconverter.service.ConverterService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConvertMenu implements MenuItem {

    private final static String NAME = "Convert currency";
    private ConverterService converterService;
    private Reader reader = new Reader();

    public ConvertMenu(ConverterService converterService) {
        this.converterService = converterService;
    }

    public void action() {
        Long id = (long) reader.getUserInput("Enter currency pair id");
        BigDecimal amount = BigDecimal.valueOf(reader.getUserInputDouble("Enter amount"));
        ConvertationResult result = converterService.convertCurrency(id, amount);
        System.out.println(amount + " " + result);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
