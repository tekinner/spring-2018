package com.mockaroo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	BufferedReader br;
	List<String> cities = new ArrayList<>();
	List<String> countries = new ArrayList<>();

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("https://mockaroo.com/");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() {

	}

	@Test(priority = 1)
	public void step3() {
		String expectedTitle = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		assertEquals(driver.getTitle(), expectedTitle);
	}

	@Test(priority = 2)
	public void step4() {
		WebElement exp1 = driver.findElement(By.xpath("//div[@class='navbar-header']//div[@class='brand']"));
		WebElement exp2 = driver.findElement(By.xpath("//div[@class='navbar-header']//div[@class='tagline']"));

		// assert that elements are displayed
		assertTrue(exp1.isDisplayed());
		assertTrue(exp2.isDisplayed());

		// assert that elements contain expected text
		assertEquals("mockaroo", exp1.getText());
		assertEquals("realistic data generator", exp2.getText());
	}

	@Test(priority = 3)
	public void step5_6() {
		// STEP 5
		// select all x buttons next to fields
		List<WebElement> xButtons = driver
				.findElements(By.xpath("//div[@id='fields']//a[@class='close remove-field remove_nested_fields']"));

		// click on all x buttons to clear fields
		for (WebElement each : xButtons) {
			each.click();
		}

		// STEP 6
		// find elements for Field Name, Type and Options
		WebElement fieldName = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Field Name']"));
		WebElement fieldType = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Type']"));
		WebElement fieldOptions = driver.findElement(By.xpath("//div[@class='column-fields']//div[.='Options']"));

		assertTrue(fieldName.isDisplayed());
		assertTrue(fieldType.isDisplayed());
		assertTrue(fieldOptions.isDisplayed());
	}

	@Test(priority = 4)
	public void step7() {
		WebElement addAnotherButton = driver.findElement(By.partialLinkText("Add another field"));
		assertTrue(addAnotherButton.isEnabled());
	}

	@Test
	public void step8() {
		String actual = driver.findElement(By.id("num_rows")).getAttribute("value");
		String expected = "1000";

		assertEquals(actual, expected);
	}

	@Test(priority = 5)
	public void step9() {
		// choose and create new Select object
		Select select = new Select(driver.findElement(By.id("schema_file_format")));

		// get the text from the chosen option in Select object
		String actual = select.getFirstSelectedOption().getText();
		String expected = "CSV";

		assertEquals(actual, expected);

		// if you want to store all options under select and want to choose a specific
		// option
		// List<WebElement> selectOptions = select.getOptions();
		// for (WebElement each : selectOptions) {
		// if(each.getText().equals("SQL")) {
		// each.click();
		// }
		// }
	}

	@Test(priority = 6)
	public void step10() {
		// choose and create new Select object
		Select select = new Select(driver.findElement(By.id("schema_line_ending")));

		// get the text from the chosen option in Select object
		String actual = select.getFirstSelectedOption().getText();
		String expected = "Unix (LF)";

		assertEquals(actual, expected);
	}

	@Test(priority = 7)
	public void step11() {
		WebElement headerChkbx = driver.findElement(By.id("schema_include_header"));
		WebElement bomChkbx = driver.findElement(By.id("schema_bom"));

		assertTrue(headerChkbx.isSelected());
		assertFalse(bomChkbx.isSelected());
	}

	@Test(priority = 8)
	public void step12_13() {
		// STEP 12
		// click on Add Another Field button
		driver.findElement(By.partialLinkText("Add another field")).click();

		// this input has dynamic id changing on every page load
		driver.findElement(By.xpath(
				"(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]"))
				.sendKeys("City");

		// STEP 13

		// click on chooose type button
		driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();

		WebElement dialogBox = driver.findElement(By.xpath("//div[@id='type_dialog']"));
		// attribute aria-hidden returns string "true" or "false". then convert it to
		// boolan with Boolean.valueOf()
		assertFalse(Boolean.valueOf(dialogBox.getAttribute("aria-hidden")));
	}

	@Test(priority = 9)
	public void step14_15_16() throws InterruptedException {
		// STEP 14
		driver.findElement(By.id("type_search_field")).sendKeys("city");
		driver.findElement(By.xpath("//div[@id='type_list']//div[.='City']")).click();
		Thread.sleep(1000);

		// STEP 15
		// click on Add Another Field button
		driver.findElement(By.partialLinkText("Add another field")).click();
		Thread.sleep(1000);
		// this input has dynamic id changing on every page load
		driver.findElement(By.xpath(
				"(//div[@id='fields']//input[starts-with(@id, 'schema_columns_attributes_')][contains(@id,'name')])[last()]"))
				.sendKeys("Country");
		Thread.sleep(1000);
		// click on chooose type button
		driver.findElement(By.xpath("(//div[@class='fields']//input[@class='btn btn-default'])[last()]")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("type_search_field")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("type_search_field")).sendKeys("country");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='type_list']//div[.='Country']")).click();
		Thread.sleep(1000);

		// STEP 16
		// click on download button
		driver.findElement(By.id("download")).click();
	}

	@Test(priority = 10)
	public void step17_18() throws InterruptedException {
		// try with resources
		Thread.sleep(5000);
		String filePath = "C:\\Users\\arslan\\Downloads\\MOCK_DATA.csv";
		// try (BufferedReader br = new BufferedReader(new FileReader(filePath));) {
		// String line = "";
		// while ((line = br.readLine()) != null)
		// data = Arrays.asList(line.split(","));
		// } catch (Exception e) {
		// System.out.println("File could not be found");
		// }

		// STEP 21 & 22 are included here
		try (FileInputStream fis = new FileInputStream(filePath);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				CSVReader reader = new CSVReader(isr)) {
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				boolean isCity = true;
				for (String e : nextLine) {
					if (isCity) {
						cities.add(e);
					} else {
						countries.add(e);
					}
					isCity = !isCity;
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}

		// STEP 18
		assertEquals("City", cities.get(0));
		assertEquals("Country", countries.get(0));

		// STEP 19
		assertEquals(1000, cities.size() - 1);
	}

	@Test(priority = 11)
	public void step22() {
		// STEP 22
		Collections.sort(cities);

		int min = cities.get(0).length();
		int max = cities.get(0).length();

		String cityShort = "";
		String cityLongest = "";

		for (String city : cities) {
			if (city.length() > max) {
				max = city.length();
				cityLongest = city;
			}

			if (city.length() < min) {
				min = city.length();
				cityShort = city;
			}
		}

		System.out.println("Shortest named city: " + "\t" + cityShort);
		System.out.println("Longest named city: " + "\t" + cityLongest);

		// STEP 23
		SortedSet<String> sortedCountry = new TreeSet<>(countries);

		for (String country : sortedCountry) {
			System.out.println(country + ":\t" + Collections.frequency(countries, country));
		}

		// STEP 24

		Set<String> citiesSet = new HashSet<>(cities);

		// STEP 25
		int uniqueCityCount = 0;

		for (int i = 0; i < cities.size(); i++) {
			if (i == cities.lastIndexOf(cities.get(i)))
				uniqueCityCount++;
		}

		System.out.println("Unique city count by for loop: " + uniqueCityCount);
		System.out.println("Unique city count by HashSet: " + citiesSet.size());

		assertEquals(uniqueCityCount, citiesSet.size());
	}

	@Test(priority = 12)
	public void step26_27() {
		// STEP 26
		Set<String> countrySet = new HashSet<>(countries);

		
		// STEP 27
		int uniqueCountryCount = 0;

		for (int i = 0; i < countries.size(); i++) {
			if (i == countries.lastIndexOf(countries.get(i)))
				uniqueCountryCount++;
		}

		System.out.println("Unique country count by for loop: " + uniqueCountryCount);
		System.out.println("Unique country count by HashSet: " + countrySet.size());

		assertEquals(uniqueCountryCount, countrySet.size());
	}

}
