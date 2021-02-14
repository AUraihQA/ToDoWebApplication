package com.acceptanceTesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();

		if (result.equals("List has been successfully created!")) {
			test.log(LogStatus.PASS, "List has been successfully created!");
		} else {
			test.log(LogStatus.FAIL, " ");
		}
		
		assertThat(result.concat("List has been successfully created!"));

	}

	@Test
	public void readOne() {
		test = report.startTest("Read a List test");

		// GIVEN: that the user has clicked the view a list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[3]"));
		targ.click();

		// WHEN: the user types the id of the list they want to view
		targ = driver.findElement(By.xpath("//*[@id=\"RListID\"]"));
		targ.sendKeys("1");

		// AND: clicks the read button
		targ = driver.findElement(By.xpath("//*[@id=\"viewList\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: they will be able to see the list they have requested to view
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));
		String result = targ.getText();
		String expected = "\"id:\"";

		if (result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, " ");
		}

		assertThat(result.contains(expected));
	}

	@Test
	public void readAll() {
		test = report.startTest("Read all Lists test");

		// GIVEN: that the user has clicked the view all lists button
		driver.get(URL);
		targ = driver.findElement(By.xpath("//*[@id=\"ViewAllLists\"]"));
		targ.click();

		// WHEN: the list loads
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));

		// THEN: they will be able to see all the lists they have created
		String result = targ.getText();
		String expected = "\"id:\"";

		if (result.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, " ");
		}

		assertThat(result.contains(expected));
	}

	@Test
	public void update() {
		test = report.startTest("Update List test");

		// GIVEN: that the user has clicked the update list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[2]"));
		targ.click();

		// WHEN: they have filled in the id of the list they want to update and the
		// updated details of the list
		targ = driver.findElement(By.xpath("//*[@id=\"UListID\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"Uname\"]"));
		targ.sendKeys("Update List Test");

		// AND: click update
		targ = driver.findElement(By.xpath("//*[@id=\"updateList\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: the details of the list should be updated with the new details they
		// have filled in
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();

		if (result.equals("List has been successfully updated!")) {
			test.log(LogStatus.PASS, "List has been successfully updated!");
		} else {
			test.log(LogStatus.FAIL, " ");
		}

		assertThat(result.concat("List has been successfully updated!"));

	}

	@Test
	public void delete() {
		test = report.startTest("Delete Lists test");

		// GIVEN: that the user has clicked delete list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[5]"));
		targ.click();

		// WHEN: they type the id of the list they want to delete
		targ = driver.findElement(By.xpath("//*[@id=\"DListID\"]"));
		targ.sendKeys("2");

		// AND: click the delete button
		targ = driver.findElement(By.xpath("//*[@id=\"deleteList\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: the list will be deleted
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();

		if (result.equals("List has been successfully deleted!")) {
			test.log(LogStatus.PASS, "List has been successfully deleted!");
		} else {
			test.log(LogStatus.FAIL, " ");
		}

		assertThat(result.concat("List has been successfully deleted!"));
	}
}
