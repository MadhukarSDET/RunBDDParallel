package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {

	public static void TakeScreenShot(WebDriver driver, String fileName) throws IOException {

		// To Access getScreenshotAs method we need this
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// this will store file content in "screenshotAs" variable as binary
		File scrrenshotContent = screenshot.getScreenshotAs(OutputType.FILE);
		// copy file content to "scrrenshot.png" file.
		File destinationFile = new File(System.getProperty("user.dir") + "//screenshots//" + fileName + "-" + getCurrentDateAndTime() + ".png");
		FileUtils.copyFile(scrrenshotContent, destinationFile);
	}
	
	public static String captureScreenshot(WebDriver driver, String fileName) throws IOException {

		// To Access getScreenshotAs method we need this
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// this will store file content in "screenshotAs" variable as binary
		String scrrenshotContent = screenshot.getScreenshotAs(OutputType.BASE64);
		// copy file content to "scrrenshot.png" file.
		return scrrenshotContent;
	}
	
	
	public static String getCurrentDateAndTime() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        String formattedDate = now.format(formatter);
        System.out.println("Formatted Date: " + formattedDate);
		return formattedDate;
	}

}
