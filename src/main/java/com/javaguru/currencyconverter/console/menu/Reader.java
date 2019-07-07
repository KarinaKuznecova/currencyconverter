package com.javaguru.currencyconverter.console.menu;

import java.util.Scanner;

public class Reader {

    public int getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public String getUserInputLine(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public double getUserInputDouble(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }
}
