package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.example.utils.JavaScriptUtils;

public class LoginPage extends Page {
	
	public LoginPage(WebDriver driver) {
        super(driver);
    }

	@FindBy(xpath = "//a[text()=' Signup / Login']")
	WebElement signupOrLogin;

	@FindBy(name = "email")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[text()=\"Login\"]")
	WebElement loginBtn;

	@FindBy(xpath = "//*[text()='Your email or password is incorrect!']")
	WebElement errorMsg;
	
	

	public void clickSignupOrLoginLink() {
		clickElement(signupOrLogin);
		//JavaScriptUtils.clickElement(driver, signupOrLogin);
	}

	public void enterCredentials(String user, String pass) {
		clearTextBox(username);
		//username.sendKeys(user);
		JavaScriptUtils.sendKeys(driver, username, user);
		clearTextBox(password);
		//password.sendKeys(pass);
		JavaScriptUtils.sendKeys(driver, password, pass);
	}

	public void clickLogin() {
		clickElement(loginBtn);
	}


	public boolean isErrorDisplayed() {
		try {
			return errorMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
