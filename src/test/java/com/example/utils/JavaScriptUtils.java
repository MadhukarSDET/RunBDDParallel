package com.example.utils;


import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;

public class JavaScriptUtils {

    private static JavascriptExecutor getJSExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    /**
     * Click element using JavaScript
     */
    public static void clickElement(WebDriver driver, WebElement element) {
    	highlightElement(driver, element);
        getJSExecutor(driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Send text into input field using JS
     */
    public static void sendKeys(WebDriver driver, WebElement element, String text) {
    	highlightElement(driver, element);
        getJSExecutor(driver).executeScript("arguments[0].value='" + text + "';", element);
    }

    /**
     * Scroll page by pixels
     */
    public static void scrollBy(WebDriver driver, int x, int y) {
        getJSExecutor(driver).executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    /**
     * Scroll to bottom of page
     */
    public static void scrollToBottom(WebDriver driver) {
        getJSExecutor(driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scroll to specific element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        getJSExecutor(driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Highlight element (adds red border)
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        getJSExecutor(driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }

    /**
     * Change element background color
     */
    public static void changeColor(WebDriver driver, WebElement element, String color) {
        getJSExecutor(driver).executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
    }

    /**
     * Get Page Title using JavaScript
     */
    public static String getPageTitle(WebDriver driver) {
        return getJSExecutor(driver).executeScript("return document.title;").toString();
    }

    /**
     * Get current URL using JavaScript
     */
    public static String getPageURL(WebDriver driver) {
        return getJSExecutor(driver).executeScript("return document.URL;").toString();
    }

    /**
     * Generate an alert popup
     */
    public static void generateAlert(WebDriver driver, String message) {
        getJSExecutor(driver).executeScript("alert('" + message + "');");
    }

    /**
     * Refresh the page using JS
     */
    public static void refreshPage(WebDriver driver) {
        getJSExecutor(driver).executeScript("history.go(0);");
    }

    /**
     * Navigate to a URL using JS
     */
    public static void navigateToURL(WebDriver driver, String url) {
        getJSExecutor(driver).executeScript("window.location='" + url + "';");
    }

    /**
     * Get inner text of an element
     */
    public static String getInnerText(WebDriver driver, WebElement element) {
        return getJSExecutor(driver).executeScript("return arguments[0].innerText;", element).toString();
    }

    /**
     * Get hidden text (from invisible element)
     */
    public static String getHiddenText(WebDriver driver, WebElement element) {
        return getJSExecutor(driver).executeScript("return arguments[0].textContent;", element).toString();
    }

    /**
     * Scroll + Click element (useful when element is below viewport)
     */
    public static void scrollAndClick(WebDriver driver, WebElement element) {
        getJSExecutor(driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", element);
    }

    /**
     * Wait until page fully loaded (document.readyState == 'complete')
     */
    public static void waitForPageLoad(WebDriver driver) {
        for (int i = 0; i < 30; i++) {
            String state = getJSExecutor(driver).executeScript("return document.readyState;").toString();
            if (state.equals("complete")) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
