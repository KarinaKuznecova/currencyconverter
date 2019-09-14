package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.service.CurrencyService;
import org.springframework.stereotype.Component;

@Component
public class DeleteCurrencyMenu implements MenuItem {

    private final static String NAME = "Delete currency";
    private CurrencyService currencyService;
    private Reader reader = new Reader();

    public DeleteCurrencyMenu(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void action() {
        long id = reader.getUserInput("Enter currency id to delete: ");
        currencyService.deleteCurrency(id);
        System.out.println("Currency deleted");
    }

    @Override
    public String toString() {
        return NAME;
    }
}
