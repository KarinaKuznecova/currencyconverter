package com.javaguru.currencyconverter.console;

import com.javaguru.currencyconverter.console.menu.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {

    private final MenuItem convertMenu;
    private final MenuItem printRates;
    private final MenuItem createNewCurrency;
    private final MenuItem createNewRate;
    private final MenuItem addRateToCurrency;
    private final MenuItem removeRate;
    private final MenuItem deleteCurrency;
    private final MenuItem setFee;
    private final MenuItem deleteFee;
    private final MenuItem exitMenu;

    @Autowired
    public ConsoleUIConfiguration(ConvertMenu convertMenu, PrintRatesMenu printRates, CreateCurrencyMenu createNewCurrency,
                                  CreateNewRateMenu createNewRate, AddRateToCurrencyMenu addRateToCurrency,
                                  RemoveRate removeRate, DeleteCurrencyMenu deleteCurrency, SetFeeMenu setFee,
                                  DeleteFeeMenu deleteFee, ExitMenu exitMenu) {
        this.convertMenu = convertMenu;
        this.printRates = printRates;
        this.createNewCurrency = createNewCurrency;
        this.createNewRate = createNewRate;
        this.addRateToCurrency = addRateToCurrency;
        this.removeRate = removeRate;
        this.deleteCurrency = deleteCurrency;
        this.setFee = setFee;
        this.deleteFee = deleteFee;
        this.exitMenu = exitMenu;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<MenuItem> actions = new ArrayList<MenuItem>();
        actions.add(convertMenu);
        actions.add(printRates);
        actions.add(createNewCurrency);
        actions.add(createNewRate);
        actions.add(addRateToCurrency);
        actions.add(removeRate);
        actions.add(deleteCurrency);
        actions.add(setFee);
        actions.add(deleteFee);
        actions.add(exitMenu);
        return new ConsoleUI(actions);
    }
}
