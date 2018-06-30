package com.ertekin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	List<String> lst = new ArrayList<>();

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://mockaroo.com/");

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	// @Test
	public void Test3() {
		String actual = driver.getTitle();
		driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String expected = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		assertEquals(actual, expected);
	}

	// @Test
	public void Test4() {
		String a = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String b = driver.findElement(By.xpath("//div[@class='tagline']")).getText();
		assertEquals(a + " " + b, "mockaroo" + " " + "realistic data generator");
	}

	@Test
	public void Test5() throws InterruptedException {

		// TASK#5 Remote all existing fields by clicking on x icon link
		List<WebElement> elements = driver
				.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));

		for (WebElement webElement : elements) {
			webElement.click();
		}

		// TASK#6 Assert that ‘Field Name’ , ‘Type’, ‘Options’ labels are displayed
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText(),
				"Field Name");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-type']")).getText(),
				"Type");
		assertEquals(driver.findElement(By.xpath("//div[@class='column column-header column-options']")).getText(),
				"Options");

		// TASK#7 Assert that ‘Add another field’ button is enabled.
		assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']"))
				.isEnabled());

		// TASK #8 Assert that default number of rows is 1000.
		assertEquals(driver.findElement(By.xpath("//input[@value='1000']")).getAttribute("value"), "1000");

		// TASK#9 Assert that default format selection is CSV
		assertEquals(driver.findElement(By.xpath("//select[@data-action='file_format']")).getAttribute("value"), "csv");

		// TASK#10 Assert that Line Ending is Unix(LF)
		assertEquals(driver.findElement(By.xpath("//select[@name='schema[line_ending]']")).getAttribute("value"),
				"unix");

		// TASK#11 Assert that header checkbox is checked and BOM is unchecked
		assertTrue(driver.findElement(By.xpath("//input[@id='schema_include_header']")).isSelected());
		assertTrue(!driver.findElement(By.xpath("//input[@id='schema_bom']")).isSelected());
	}

	@Test
	public void Test6() throws InterruptedException {
		// TASK#12 & 13 Click on ‘Add another field’ and enter name “City”
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("(//div[@class='fields']//input[@placeholder='enter name...'])[last()]"))
				.sendKeys("City");
		driver.findElement(By.xpath("(//input[@class='btn btn-default'])[last()]")).click();

		Thread.sleep(1000);
		assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Choose a Type')]")).getText(), "Choose a Type");

		// TASK#13 Click on ‘Add another field’ and enter name “City”

		driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("City");
		driver.findElement(By.xpath("//div[@class='examples']")).click();

		// TASK#15 Click on ‘Add another field’ and enter name “City”

		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();

		driver.findElement(By.xpath("(//div[@class='fields']//input[@placeholder='enter name...'])[last()]"))
				.sendKeys("Country");
		driver.findElement(By.xpath("(//input[@class='btn btn-default'])[last()]")).click();

		Thread.sleep(1000);
		assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Choose a Type')]")).getText(), "Choose a Type");

		driver.findElement(By.xpath("//input[@id='type_search_field']")).sendKeys("Country");
		driver.findElement(By.xpath("//div[@class='examples']")).click();
		Thread.sleep(1000);

	}

	@Test
	public void Test7() {

		driver.findElement(By.xpath("//button[@id='download']")).click();

		try (FileReader fr = new FileReader("/Users/ertekinkoc/Downloads/MOCK_DATA.csv");
				BufferedReader br = new BufferedReader(fr);) {

			String s = null;
			while ((s = br.readLine()) != null) {

				lst.add(s);
			}

			assertEquals(lst.get(0), "City,Country");
			assertEquals(lst.size() - 1, 1000);

			System.out.println(lst.get(0));
			System.out.println(lst.get(1));
			System.out.println(lst.size());

		} catch (Exception e) {
			System.err.println(
					"File Not Found, please check your file at C:\\Users\\Technoline\\Downloads\\MOCK_DATA.csv ");
		}

	}

	@Test
	public void Test8() {

		List<String> cities = new ArrayList<>();
		List<String> countries = new ArrayList<>();

		for (String each : lst) {
			String[] a = each.split(",");
			cities.add(a[0]);
			countries.add(a[1]);
		}

		cities.remove(0);
		countries.remove(0);

		Collections.sort(cities);
		Collections.sort(countries);

		int longest = 0;
		int shortest = 100;
		
		for (String each : cities) {
			if (each.length()>longest) longest = each.length();
			if (each.length()<shortest) shortest = each.length();
		}


	}

	
	
	
	@AfterClass
	public void afterClass() {
		// driver.close();
	}
}
