package com.ertekin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://mockaroo.com/");

	}
	
	//@Test
	public void Test3() {
		String actual = driver.getTitle();
		driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String expected = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		assertEquals(actual, expected);
	}
	
	//@Test
	public void Test4() {
		String a = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String b = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		assertEquals(a+" "+b, "mockaroo"+" "+"realistic data generator");
	}
	
	@Test
	public void Test5() throws InterruptedException {
		//TASK #5
		List<WebElement> elements = driver.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));
 
		for (WebElement webElement : elements) {
            webElement.click();
        }
		//TASK #6
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText(), "Field Name");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText(), "Type");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText(), "Options");
		//TASK #7
		assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).isEnabled());
		//TASK #8
		assertEquals(driver.findElement(By.xpath("//input[@value='1000']")).getAttribute("value"), "1000");
		//TASK #9
		assertEquals(driver.findElement(By.xpath("//select[@data-action='file_format']")).getAttribute("value"), "csv");
		//TASK #10
		assertEquals(driver.findElement(By.xpath("//select[@name='schema[line_ending]']")).getAttribute("value"), "unix");

		
	}
	
	
	
	
	@AfterClass
	public void afterClass() {
	//	driver.close();
	}
}
