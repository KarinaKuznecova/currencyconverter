package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.ConverterService;
import org.springframework.stereotype.Component;

@Component
public class DeleteFeeMenu implements MenuItem {

    private final static String NAME = "Remove fee";
    private ConverterService converterService;
    private Reader reader = new Reader();

    public DeleteFeeMenu(ConverterService converterService) {
        this.converterService = converterService;
    }

    public void action() {
        Long id = (long)reader.getUserInput("Enter currency pair id");
        converterService.deleteFee(id);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
