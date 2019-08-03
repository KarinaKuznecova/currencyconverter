package com.javaguru.currencyconverter.console.menu;

import com.javaguru.currencyconverter.domain.Currency;
import com.javaguru.currencyconverter.service.CurrencyService;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class CreateCurrencyMenu implements MenuItem {

    private final static String NAME = "Create new currency";
    private CurrencyService currencyService;
    private Reader reader = new Reader();

    public CreateCurrencyMenu(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void action() {
        String currencyCode = reader.getUserInputLine("Enter new currency code:");
        String dateFormatString = "YYYY-MM-DD";
        String dateString = reader.getUserInputLine("Enter effective date in format " + dateFormatString);
        if (dateIsCorrect(dateString, dateFormatString)) {
            Currency currency = new Currency();
            currency.setBase(currencyCode);
            currency.setDate(dateString);
            currencyService.createOrUpdateCurrency(currency);
            System.out.println("Currency with id " + currency.getId() + " created");
        }
    }

    private boolean dateIsCorrect(String dateString, String dateFormatString) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException ex) {
            ex.getStackTrace();
            System.out.println("Date is incorrect");
            return false;
        }
    }

    @Override
    public String toString() {
        return NAME;
    }
}
