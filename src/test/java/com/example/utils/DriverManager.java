package com.example.utils;


public class DriverManager {

    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    public static void setBrowser(String browser) {
        browserName.set(browser);
    }

    public static String getBrowser() {
        return browserName.get();
    }
}
