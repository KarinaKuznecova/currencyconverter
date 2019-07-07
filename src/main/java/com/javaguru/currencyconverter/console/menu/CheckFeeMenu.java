package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.ConverterService;
import org.springframework.stereotype.Component;

@Component
public class CheckFeeMenu implements MenuItem {

    private final static String NAME = "Check fee";
    private ConverterService converterService;
    private Reader reader = new Reader();

    public CheckFeeMenu(ConverterService converterService) {
        this.converterService = converterService;
    }

    public void action() {
        Long id = (long)reader.getUserInput("Enter currency pair id");
        System.out.println("Fee for this currency pair is " + converterService.checkFee(id));
    }

    @Override
    public String toString() {
        return NAME;
    }
}
