package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.domain.Rate;
import com.javaguru.currencyconverter.service.RateService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateNewRateMenu implements MenuItem {

    private final static String NAME = "Create new rate";
    private RateService rateService;
    private Reader reader = new Reader();

    public CreateNewRateMenu(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public void action() {
        long currencyId = reader.getUserInput("Enter primary currency id :");
        String secondaryCurrency = reader.getUserInputLine("Enter secondary currency code: ");
        BigDecimal rateValue = BigDecimal.valueOf(reader.getUserInputDouble("Enter rate: "));

        Rate rate = new Rate();
        rate.setCurrencyId(currencyId);
        rate.setCurrencyName(secondaryCurrency);
        rate.setRate(rateValue);

        rateService.createNewRate(rate);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
