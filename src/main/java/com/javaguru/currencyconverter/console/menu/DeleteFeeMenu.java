package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.RateService;
import org.springframework.stereotype.Component;

@Component
public class DeleteFeeMenu implements MenuItem {

    private final static String NAME = "Remove fee";
    private RateService rateService;
    private Reader reader = new Reader();

    public DeleteFeeMenu(RateService rateService) {
        this.rateService = rateService;
    }

    public void action() {
        Long id = (long) reader.getUserInput("Enter currency pair id");
        rateService.deleteFee(id);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
