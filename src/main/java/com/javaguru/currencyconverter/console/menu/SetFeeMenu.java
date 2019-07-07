package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.ConverterService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SetFeeMenu implements MenuItem {

    private final static String NAME = "Set fee";
    private ConverterService converterService;
    private Reader reader = new Reader();

    public SetFeeMenu(ConverterService converterService) {
        this.converterService = converterService;
    }

    public void action() {
        Long id = (long)reader.getUserInput("Enter currency pair id");
        BigDecimal newFee = BigDecimal.valueOf(reader.getUserInputDouble("Enter new fee:"));
        converterService.setFee(id, newFee);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
