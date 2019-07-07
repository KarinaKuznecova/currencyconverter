package com.javaguru.currencyconverter.console.menu;

import org.springframework.stereotype.Component;

@Component
public class ExitMenu implements MenuItem {

    private final static String NAME = "Exit";

    public void action() {
        System.exit(0);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
