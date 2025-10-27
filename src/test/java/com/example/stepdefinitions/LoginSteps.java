package com.example.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.utils.DriverFactory;
import com.example.utils.ExcelUtility;
import com.example.utils.ScreenShotUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	WebDriver driver=DriverFactory.getDriver();
	
	private List<Map<String, String>> userData;
    LoginPage loginPage;

	@Given("user is on login page")
	public void user_is_on_login_page() {
		loginPage = new LoginPage(driver);
		loginPage.clickSignupOrLoginLink();
	}

	@When("user enters username {string} and password {string}")
	public void user_enters_username_and_password(String username, String password) {
		loginPage.enterCredentials(username, password);
	}

	@When("user should login with valid credentials")
	public void enter_username_and_password() throws IOException, InterruptedException {

		for (Map<String, String> map : userData) {

			System.out.println(userData.size());
			System.out.println(map.get("username") + " " + map.get("password"));

			loginPage.enterCredentials(map.get("username"), map.get("password"));
			loginPage.clickLogin();
			try {
				Assert.assertTrue(new HomePage(driver).isOnHomePage());
			} catch (Exception e) {
				ScreenShotUtil.TakeScreenShot(driver, "Login-Failed");
				//Assert.assertTrue("Error message not shown!", loginPage.isErrorDisplayed());
			}
			new HomePage(driver).logoutFromAPP();
		}

	}

	@When("user clicks login")
	public void user_clicks_login() {
		loginPage.clickLogin();
	}

	@Then("user should see the home page")
	public void user_should_see_the_home_page() throws IOException {

		try {
			Assert.assertTrue(new HomePage(driver).isOnHomePage());
		} catch (AssertionError e) {
			ScreenShotUtil.TakeScreenShot(driver, "Login-Failed");
			assertTrue(false);
			// Assert.assertTrue("Error message not shown!", loginPage.isErrorDisplayed());
		}
		// kept this inside @After so that it will execute end of each scenario
		// DriverFactory.quitDriver();
	}

	@Then("user should see an error message")
	public void user_should_see_an_error_message() {
		Assert.assertTrue(loginPage.isErrorDisplayed());
		DriverFactory.quitDriver();
	}

	@When("user enters username and password")
	public void login(DataTable data) {

		List<Map<String, String>> map = data.asMaps();

		for (Map<String, String> testdata : map) {

			loginPage.enterCredentials(testdata.get("username"), testdata.get("password"));
		}
	}

	@When("user enters username and password datatable")
	public void loginDataTable(DataTable dataTable) {

		List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : table) {
			String field = row.get("field");
			String value = row.get("value");
			System.out.println(field + " → " + value);
		}
	}

	@When("I read login test data from {string} and sheet {string}")
	public void loginDataTable(String fileName, String sheetName) {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/" + fileName;
		userData = ExcelUtility.getTestData(filePath, sheetName);
		System.out.println("Loaded test data: " + userData.size() + " rows");
	}
}
