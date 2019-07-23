package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.service.CurrencyService;
import com.javaguru.currencyconverter.service.RateService;
import org.springframework.stereotype.Component;

@Component
public class AddRateToCurrencyMenu implements MenuItem {

    private final static String NAME = "Add existing rate to currency";
    private CurrencyService currencyService;
    private RateService rateService;
    private Reader reader = new Reader();

    public AddRateToCurrencyMenu(CurrencyService currencyService, RateService rateService) {
        this.currencyService = currencyService;
        this.rateService = rateService;
    }

    @Override
    public void action() {
        long rateId = reader.getUserInput("Enter rate id: ");
        long currencyId = reader.getUserInput("Enter currency id: ");
        Rate rate = rateService.getRateById(rateId);
        currencyService.addRateToCurrency(currencyId, rate);
        System.out.println("Rate added");
    }

    @Override
    public String toString() {
        return NAME;
    }
}
