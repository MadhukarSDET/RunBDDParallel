package com.example.runners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.example.utils.DriverManager;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.example.stepdefinitions",
		"com.example.hooks"},

		plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json", }, tags = "@smoke", monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
	
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void getBrowser(String browser) {
		System.out.println("Inside @BeforeTest");
		//System.setProperty("browser",browser);
		DriverManager.setBrowser(browser);
	}

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
