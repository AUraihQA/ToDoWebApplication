package com.acceptanceTesting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")

public class ListPage {
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Lists.html";

	@BeforeAll
	public static void Beforeall() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();

	}


	@AfterAll
	public static void Afterall() {
		driver.quit();


	}

	@Test
	public void createList() throws InterruptedException {

		// GIVEN: that the user has clicked the create list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[1]"));
		targ.click();

		// WHEN: they fill in the details for the list
		targ = driver.findElement(By.xpath("//*[@id=\"Cname\"]"));
		targ.sendKeys("New List");

		// AND: click create
		targ = driver.findElement(By.xpath("/html/body/nav/div[1]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN the list will be created and they can start adding to do’s
		targ = new WebDriverWait(driver, 50)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();
		assertThat(result.contains("successful"));


	}

	@Test()
	public void readOne() throws InterruptedException {

		// GIVEN: that the user has clicked the view a list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[3]"));
		targ.click();

		// WHEN: the user types the id of the list they want to view
		targ = driver.findElement(By.xpath("//*[@id=\"RListID\"]"));
		targ.sendKeys("1");

		// AND: clicks the read button
		targ = driver.findElement(By.xpath("/html/body/nav/div[3]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: they will be able to see the list they have requested to view
		targ = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));
		String result = targ.getText();
		String expected = "id : ";

		assertThat(result.contains(expected));


	}

	@Test
	public void readAll() throws InterruptedException {

		// GIVEN: that the user has clicked the view all lists button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[4]"));
		targ.click();

		// WHEN: the list loads
		targ = new WebDriverWait(driver, 30)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));

		// THEN: they will be able to see all the lists they have created
		String result = targ.getText();
		String expected = "id : ";

		assertThat(result.contains(expected));

	}

	@Test
	public void update() throws InterruptedException {

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
		targ = driver.findElement(By.xpath("/html/body/nav/div[2]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: the details of the list should be updated with the new details they
		// have filled in
		targ = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();

		assertThat(result.contains("successful"));

	}

	@Test
	public void delete() throws InterruptedException {

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
		targ = new WebDriverWait(driver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();
		
		assertThat(result.contains("successful"));

	}
}
