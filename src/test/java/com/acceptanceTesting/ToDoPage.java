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
public class ToDoPage {

	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/ToDo.html";

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
	public void createToDo() {

		// GIVEN: that the user has clicked the create to do button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[1]"));
		targ.click();

		// WHEN: they fill in the details for the to do
		targ = driver.findElement(By.xpath("//*[@id=\"Cdescription\"]"));
		targ.sendKeys("CreateTest");
		targ = driver.findElement(By.xpath("//*[@id=\"CdateCreated\"]"));
		targ.sendKeys("12thFebruary2021");
		targ = driver.findElement(By.xpath("//*[@id=\"CdeadlineDate\"]"));
		targ.sendKeys("22ndFebruary2021");
		targ = driver.findElement(By.xpath("//*[@id=\"CCompletion\"]"));
		targ.sendKeys("No");
		targ = driver.findElement(By.xpath("//*[@id=\"CListID\"]"));
		targ.sendKeys("1");

		// AND: click create
		targ = driver.findElement(By.xpath("/html/body/nav/div[1]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN the to do will be created and they can start adding to do’s
		targ = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();
		
		assertThat(result.contains("successful"));

	}
	
	@Test
	public void readOne() {

		// GIVEN: that the user has clicked the view a to do button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[3]"));
		targ.click();

		// WHEN: the user types the id of the to do they want to view
		targ = driver.findElement(By.xpath("//*[@id=\"RToDoID\"]"));
		targ.sendKeys("1");

		// AND: clicks the read button
		targ = driver.findElement(By.xpath("//*[@id=\"viewToDo\"]/div/div/div[3]/button[1]"));
		targ.click();

		// THEN: they will be able to see the to do they have requested to view
		targ = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));
		String result = targ.getText();
		String expected = "id : ";

		assertThat(result.contains(expected));
	}
	
	@Test
	public void readAll() {

		// GIVEN: that the user has clicked the view all to do's button
		driver.get(URL);
		targ = driver.findElement(By.xpath("//*[@id=\"ViewAllToDo\"]"));
		targ.click();

		// WHEN: the to do's loads
		targ = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"space\"]")));

		// THEN: they will be able to see all the to do's they have created
		String result = targ.getText();
		String expected = "id : ";

		assertThat(result.contains(expected));
	}
	
	@Test
	public void update() {

		// GIVEN: that the user has clicked the update to do button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[2]"));
		targ.click();

		// WHEN: they have filled in the id of the to do they want to update and the updated details of the to do 
		targ = driver.findElement(By.xpath("//*[@id=\"UToDoID\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"Udescription\"]"));
		targ.sendKeys("Update to do test");
		targ = driver.findElement(By.xpath("//*[@id=\"UdateCreated\"]"));
		targ.sendKeys("12thFebruary2021");
		targ = driver.findElement(By.xpath("//*[@id=\"UdeadlineDate\"]"));
		targ.sendKeys("22ndFebruary2021");
		targ = driver.findElement(By.xpath("//*[@id=\"UCompletion\"]"));
		targ.sendKeys("No");
		targ = driver.findElement(By.xpath("//*[@id=\"UListID\"]"));
		targ.sendKeys("1");

		// AND: click update
		targ = driver.findElement(By.xpath("//*[@id=\"updateToDo\"]/div/div/div[3]/button[1]"));
		targ.click();
		
		//THEN: the details of the to do should be updated with the new details they have filled in
		targ = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();
		
		assertThat(result.contains("successful"));
		
	}
	
	@Test
	public void delete() {

		// GIVEN: that the user has clicked delete to do button
		driver.get(URL);
		targ = driver.findElement(By.xpath("/html/body/nav/nav[2]/nav[2]/nav/button[5]"));
		targ.click();
		
		//WHEN: they type the id of the to do they want to delete
		targ = driver.findElement(By.xpath("//*[@id=\"DToDoID\"]"));
		targ.sendKeys("1");
		
		//AND: click the delete button
		targ = driver.findElement(By.xpath("//*[@id=\"deleteToDo\"]/div/div/div[3]/button[1]"));
		targ.click();
		
		//THEN: the to do will be deleted
		targ = new WebDriverWait(driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"onsuccess\"]")));
		String result = targ.getText();

		assertThat(result.contains("successful"));
		
	}


}
