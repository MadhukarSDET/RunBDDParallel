package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    
    

    public static WebDriver initDriver(String browser,boolean enableHeadless) {
    	
        if ("chrome".equalsIgnoreCase(browser)) {
        	ChromeOptions options = new ChromeOptions();
        	if(enableHeadless) {
        		options.addArguments("--headless=new");
        	}
        	//--mute-audio
            options.addArguments("--disable-notifications","--incognito","--no-sandbox","--disable-dev-shm-usage","--window-size=1920,1080");
            tlDriver.set(new ChromeDriver(options));
        } else if ("edge".equalsIgnoreCase(browser)) {
        	EdgeOptions options = new EdgeOptions();
            // Enable headless mode
        	if(enableHeadless) {
        		options.addArguments("--headless=new");
        	}
            options.addArguments("--window-size=1920,600");
            tlDriver.set(new EdgeDriver(options));
        } else if ("firefox".equalsIgnoreCase(browser) || "ff".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            if(enableHeadless) {
        		options.addArguments("--headless=new");
        	}
            tlDriver.set(new FirefoxDriver(options));
        } else {
        	ChromeOptions options = new ChromeOptions();
        	if(enableHeadless) {
        		options.addArguments("--headless=new");
        	}
            tlDriver.set(new ChromeDriver(options));
        }
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver() {
    	 if (tlDriver.get() == null) {
             throw new IllegalStateException("WebDriver is not initialized. Call initDriver() first.");
         }
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
