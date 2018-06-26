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
import org.openqa.selenium.support.ui.Select;
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
		//TASK #7 Add another field
		assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).isEnabled());
		//TASK #8 Assert that default number of rows is 1000
		assertEquals(driver.findElement(By.xpath("//input[@value='1000']")).getAttribute("value"), "1000");
		//TASK #9 Assert that default format selection is CSV 
		assertEquals(driver.findElement(By.xpath("//select[@data-action='file_format']")).getAttribute("value"), "csv");
		String str = driver.findElement(By.xpath("//select[@data-action='file_format']")).getAttribute("value");
		
		Select select = new Select(driver.findElement(By.xpath("//select[@data-action='file_format']")));
		String actual  = select.getFirstSelectedOption().getText();
		String expected = "CSV";
		assertEquals(actual, expected);

		
		
		//TASK #10 Assert that Line Ending is Unix(LF) 
		assertEquals(driver.findElement(By.xpath("//select[@name='schema[line_ending]']")).getAttribute("value"), "unix");
		Select select2 = new Select(driver.findElement(By.xpath("//select[@id='schema_line_ending']")));
		assertEquals(select2.getFirstSelectedOption().getText(), "Unix (LF)");

		//TASK #11  Assert that header checkbox is checked and BOM is unchecked
		assertTrue(!driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected());
		
		
		//TASK #12 Click on ‘Add another field’ and enter name “City”
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath(
			"(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]"))
				.sendKeys("City");
		
		
		driver.findElement(By.xpath("//input[@value='Row Number']")).getClass();
		driver.findElement(By.xpath("//input[@value='Row Number']")).click();
		
		
	}
	
	
	
	
	@AfterClass
	public void afterClass() {
	//	driver.close();
	}
}
