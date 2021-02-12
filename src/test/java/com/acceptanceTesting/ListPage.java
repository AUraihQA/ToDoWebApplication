package com.acceptanceTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ListPage {
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Lists.html";
	private static ExtentReports report;
	private static ExtentTest test;

	@BeforeAll
	public static void Beforeall() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();

		report = new ExtentReports("target/reports/listPage.html", true);
	}

	@AfterEach
	public void endTest() {
		report.endTest(test);
	}

	@AfterAll
	public static void Afterall() {
		driver.quit();

		report.flush();
		report.close();

	}

	@Test
	public void createList() {

		test = report.startTest("Add List test");

		// GIVEN: that the user has clicked the create list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[1]"));
		targ.click();

		// WHEN: they fill in the details for the list
		targ = driver.findElement(By.xpath("//*[@id=\"Cname\"]"));
		targ.sendKeys("New List");

		// AND: click create
		targ = driver.findElement(By.xpath("//*[@id=\"createList\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN the list will be created and they can start adding to do’s
		targ = driver.findElement(By.xpath("//*[@id=\"onsuccess\"]"));
		String result = targ.getText();

		assertEquals("List has been successfully created!", result);

		if (result.equals("List has been successfully created!")) {
			test.log(LogStatus.PASS, "List has been successfully created!");
		} else {
			test.log(LogStatus.FAIL, "List has been successfully created!");
		}

	}
}
