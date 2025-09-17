package com.javahelper.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);
    public static String prompt(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine().trim();
    }
}
