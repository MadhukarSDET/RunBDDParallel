package com.example.hooks;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.Parameter;
import com.example.utils.ConfigReader;
import com.example.utils.DriverFactory;
import com.example.utils.DriverManager;
import com.example.utils.ScreenShotUtil;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> scenarioTest;

	@BeforeAll
	public static void beforeAll() {
		// initialize report once
		extent = ExtentManager.getInstance();
		scenarioTest = new ThreadLocal<>();
	}

	@Before
	public void beforeScenario(Scenario scenario) {

		System.out.println("Broser in Hooks:" + DriverManager.getBrowser());
		ExtentTest test = extent.createTest(scenario.getName());
		scenarioTest.set(test);
		test.info("Scenario started: " + scenario.getName());
		DriverFactory.initDriver(DriverManager.getBrowser(),
				Boolean.parseBoolean(ConfigReader.get("enableHeadless")));
		DriverFactory.getDriver().get(ConfigReader.get("url"));
	}

	@After
	public void afterScenario(Scenario scenario) {
		ExtentTest test = scenarioTest.get();
		if (scenario.isFailed()) {
			test.fail("Scenario Failed: " + scenario.getName());
			try {

				String captureScreenshot = ScreenShotUtil.captureScreenshot(DriverFactory.getDriver(),
						scenario.getName());
				test.addScreenCaptureFromBase64String(captureScreenshot, scenario.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			test.pass("Scenario Passed: " + scenario.getName());
		}
		DriverFactory.quitDriver();
	}

	@AfterAll
	public static void afterAll() {
		ExtentManager.getInstance().flush();
	}
}
