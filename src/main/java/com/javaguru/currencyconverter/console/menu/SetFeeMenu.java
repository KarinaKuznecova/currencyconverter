package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.RateService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SetFeeMenu implements MenuItem {

    private final static String NAME = "Set fee";
    private RateService rateService;
    private Reader reader = new Reader();

    public SetFeeMenu(RateService rateService) {
        this.rateService = rateService;
    }

    public void action() {
        Long id = (long) reader.getUserInput("Enter currency pair id");
        BigDecimal newFee = BigDecimal.valueOf(reader.getUserInputDouble("Enter new fee:"));
        rateService.setFee(id, newFee);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
