package com.javaguru.currencyconverter.console;

import com.javaguru.currencyconverter.console.menu.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfiguration {

    private final MenuItem convertMenu;
    private final MenuItem printRates;
    private final MenuItem setFee;
    private final MenuItem checkFee;
    private final MenuItem deleteFee;
    private final MenuItem exitMenu;

    public ConsoleUIConfiguration(ConvertMenu convertMenu, PrintRatesMenu printRates, SetFeeMenu setFee, CheckFeeMenu checkFee,
                                  DeleteFeeMenu deleteFee, ExitMenu exitMenu) {
        this.convertMenu = convertMenu;
        this.printRates = printRates;
        this.setFee = setFee;
        this.checkFee = checkFee;
        this.deleteFee = deleteFee;
        this.exitMenu = exitMenu;
    }

    @Bean
    ConsoleUI consoleUI (){
        List<MenuItem> actions = new ArrayList<MenuItem>();
        actions.add(convertMenu);
        actions.add(printRates);
        actions.add(setFee);
        actions.add(checkFee);
        actions.add(deleteFee);
        actions.add(exitMenu);
        return new ConsoleUI(actions);
    }
}
