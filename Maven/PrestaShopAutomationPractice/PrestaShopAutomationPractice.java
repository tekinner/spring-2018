package com.prestashop.tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopAutomationPractice {

	WebDriver driver ;
	Fake f = new Fake();
	ArrayList<String> str = new ArrayList<>();

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void Before() {
		driver.get("http://automationpractice.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


	}
	
	//test scenario: wrong credentials test
	@Test
	public void Test1() {
		driver.findElement(By.cssSelector("a[class='login']")).click();
		driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control'][@id='email']")).
		sendKeys("random@gmail.com");
		driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control'][@id='passwd']")).
		sendKeys("random@12345"+ Keys.ENTER);
		
		String check = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
		Assert.assertEquals(check, "Authentication failed.");
	}

	//test scenario: invalid email test
	@Test
	public void Test2() {
		driver.findElement(By.cssSelector("a[class='login']")).click();
		driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control'][@id='email']")).
		sendKeys("randomgmail.com"+Keys.ENTER);
		String check = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
		Assert.assertEquals(check, "Invalid email address.");
	}	

	//test scenario: blank email test
	@Test
	public void Test3() {
		driver.findElement(By.cssSelector("a[class='login']")).click();
		driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control'][@id='email']")).
		sendKeys(" "+Keys.ENTER);
		  
		String check = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
		Assert.assertEquals(check, "An email address required.");
	}

	//test scenario: blank password test
	@Test
	public void Test4() {
		str = f.faker();
	//	System.out.println(str);
		driver.findElement(By.cssSelector("a[class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email_create']")).
		sendKeys(str.get(0)+Keys.ENTER);//ramdom email
				
		//Title Selection
		if(f.number(1,2)==1) driver.findElement(By.id("id_gender1")).click();
		else driver.findElement(By.id("id_gender2")).click();
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(str.get(2));
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(str.get(3));
		
		driver.findElement(By.xpath("//input[@id='passwd']")).
		sendKeys(str.get(0));//ramdom email
		
		Select daysElement = new Select(driver.findElement(By.xpath("//select[@id='days']"))); 
		daysElement.selectByIndex(f.number(1,  31));
		Select monthsElement = new Select(driver.findElement(By.xpath("//select[@id='months']"))); 
		monthsElement.selectByIndex(f.number(1,  12));
		Select yearsElement = new Select(driver.findElement(By.xpath("//select[@id='years']"))); 
		yearsElement.selectByValue(String.valueOf(f.number(1900,  2018)));
	
		
		
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(str.get(4));
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys(str.get(5));
		
		Select stateElement = new Select(driver.findElement(By.xpath("//select[@id='id_state']"))); 
		stateElement.selectByValue(String.valueOf(f.number(1, 50)));
		
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(str.get(10));
		driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys(str.get(8));
		driver.findElement(By.xpath("//input[@id='alias']")).sendKeys(str.get(9));
		driver.findElement(By.xpath("//button[@type='submit'][span='Register']")).click();
	
		String actual = str.get(2)+" "+str.get(3); 
		String expected = driver.findElement(By.xpath("//a[@class='account']/span")).getText();
	
		Assert. assertEquals(actual, expected);
//		System.out.println(actual.equals(expected));
	}
	
	@AfterClass
	public void afterMethod(){
		//driver.close();
	}
	
}
