package com.javaguru.currencyconverter.console;

import com.javaguru.currencyconverter.console.menu.Reader;
import com.javaguru.currencyconverter.console.menu.MenuItem;

import java.util.List;

public class ConsoleUI {

    private final List<MenuItem> menuItems;
    private Reader reader = new Reader();

    public ConsoleUI(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        while (true) {
            try {
                printMenu();
                int pickedNumber = reader.getUserInput("Enter number: ");
                menuItems.get(pickedNumber - 1).action();
            } catch (Exception e) {
                System.out.println("Something went wrong");
                System.out.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + 1 + ". " + menuItems.get(i));
        }
    }
}
