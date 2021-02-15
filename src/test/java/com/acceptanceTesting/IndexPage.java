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
public class IndexPage {

	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/index.html";
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
	public void createList() {


		// GIVEN: that the user has clicked the create list button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[1]"));
		targ.click();

		// WHEN: they fill in the details for the list
		targ = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		targ.sendKeys("New List");

		// AND: click create
		targ = driver.findElement(By.xpath("//*[@id=\"createList\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN the list will be created and they can start adding to do’s

		targ = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();
		
		assertThat(result.contains("successful"));
	}
	
	

	@Test
	public void createToDo() {

		// GIVEN: that the user has clicked the create to do button

		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[2]"));
		targ.click();

		// WHEN: they fill in the details for the to do
		targ.findElement(By.xpath("//*[@id=\"description\"]"));
		targ.sendKeys("DoSomething");
		targ.findElement(By.xpath("//*[@id=\"dateCreated\"]"));
		targ.sendKeys("12thFebruary2021");
		targ.findElement(By.xpath("//*[@id=\"deadlineDate\"]"));
		targ.sendKeys("22ndFebruary2021");
		targ.findElement(By.xpath("//*[@id=\"Completion\"]"));
		targ.sendKeys("No");
		targ.findElement(By.xpath("//*[@id=\"ListID\"]"));
		targ.sendKeys("1");

		// AND: click create
		targ = driver.findElement(By.xpath("//*[@id=\"createToDo\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN the to do will be created and they can start adding to do’s
		targ = driver.findElement(By.xpath("//*[@id=\"onsuccess\"]"));
		String result = targ.getText();
		
		assertThat(result.contains("successful"));

	}

}
