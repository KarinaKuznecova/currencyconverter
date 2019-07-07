package com.javaguru.currencyconverter;

import com.javaguru.currencyconverter.config.AppConfig;
import com.javaguru.currencyconverter.console.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CurrencyConverterApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.start();
    }
}
