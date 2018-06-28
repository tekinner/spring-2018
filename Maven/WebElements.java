package com.erti;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;
	Faker f = new Faker();

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	// @Test
	public void myLinks() {

		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		//
		// for (WebElement each : links) {
		// System.out.println(each.getText());
		// }

	}

	// @Test
	public void myLinks2() {

		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		System.out.println(links.size());

		List<String> arr = new ArrayList<>();
		for (WebElement each : links) {
			if (!each.getText().isEmpty()) {
				arr.add(each.getText());
			}

		}
		System.out.println(arr.toString());

	}

	@Test
	public void myLinks3() throws InterruptedException {

		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> texts = driver.findElements(By.xpath("//input[@type='text']"));
		List<WebElement> drops1 = driver.findElements(By.xpath("//div[@class='tempContDiv']//select"));
		List<WebElement> drops2 = driver.findElements(By.tagName("select"));
		List<WebElement> check = driver.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> radio = driver.findElements(By.xpath("//input[@type='radio']"));

		assertEquals(texts.size(), 2);
		assertEquals(drops1.size(), 3);
		assertEquals(check.size(), 9, "This message will be seen if it fails");
		assertEquals(radio.size(), 9);

		for (WebElement each : texts) {
			each.sendKeys(f.name().firstName());
		}

		// Selecting each element of Selecet element
		Select selector = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		int n = f.random().nextInt(1, 3);
		selector.selectByIndex(n);

		List<WebElement> ls = selector.getOptions();
		for (WebElement each : ls) {
			selector.selectByVisibleText(each.getText());
			// Thread.sleep(500);
		}
		// Storing all Select element
		List<WebElement> selects = driver.findElements(By.xpath("//select"));

		for (WebElement each : selects) {

			int rand = f.random().nextInt(1, 3);
			Select sel = new Select(each);
			sel.selectByIndex(rand);

		}

		// select random checkboxes

		List<WebElement> ls2 = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (WebElement each : ls2) {
			boolean b = f.random().nextBoolean();
			if (b)
				each.click();
			Thread.sleep(500);

		}

		// select random radiButtons
		// List<WebElement> radios =
		// driver.findElements(By.xpath("//input[@type='radio']"));
		// System.out.println(radios.get(0));
		//
		// for (WebElement each : radios) {
		//
		// boolean b = f.random().nextBoolean();
		// if (b)
		// each.click();
		// Thread.sleep(500);
		// }

		// Select random element of each row

		for (int i = 1; i <= 3; i++) {
			int n1 = f.random().nextInt(1, 3);
			driver.findElement(By.xpath("//input[@id='MatrixChoice1_row" + i + "_column" + n1 + "']")).click();

		}
		// MatrixChoice1_row2_column3

	}

}

/*
 * Homework: Loop through each inputbox and enter random names Loop through each
 * dropDown and randomly select by index Loop through each checkBoxes and select
 * each one Loop through each radioButton and click one by one by waiting one
 * second intervals click all buttons
 * 
 * 
 * 
 * 
 * 
 * Select daysElement = new
 * Select(driver.findElement(By.xpath("//select[@id='days']")));
 * daysElement.selectByIndex(f.number(1, 31)); Select monthsElement = new
 * Select(driver.findElement(By.xpath("//select[@id='months']")));
 * monthsElement.selectByIndex(f.number(1, 12)); Select yearsElement = new
 * Select(driver.findElement(By.xpath("//select[@id='years']")));
 * yearsElement.selectByValue(String.valueOf(f.number(1900, 2018)));
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
