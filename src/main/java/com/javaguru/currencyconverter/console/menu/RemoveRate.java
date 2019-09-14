package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.service.CurrencyService;
import com.javaguru.currencyconverter.service.RateService;
import org.springframework.stereotype.Component;

@Component
public class RemoveRate implements MenuItem {

    private final static String NAME = "Remove rate";
    private CurrencyService currencyService;
    private RateService rateService;
    private Reader reader = new Reader();

    public RemoveRate(CurrencyService currencyService, RateService rateService) {
        this.currencyService = currencyService;
        this.rateService = rateService;
    }

    @Override
    public void action() {
        long currencyId = reader.getUserInput("Enter rate id to remove");
        long rateId = reader.getUserInput("Enter rate id to remove");
        Rate rate = rateService.getRateById(rateId);
        currencyService.removeRateForCurrency(currencyId, rate);
        System.out.println("Rate removed");
    }

    @Override
    public String toString() {
        return NAME;
    }
}
