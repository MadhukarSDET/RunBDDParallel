package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()=\" Logout\"]")
	WebElement logoutLnk;

	public void logoutFromAPP() {
		logoutLnk.click();
	}

	public boolean isOnHomePage() {
		try {
			// return driver.getTitle().toLowerCase().contains("home") ||
			// driver.getCurrentUrl().contains("home");
			return waitForElementToBeClickable(driver, logoutLnk).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
