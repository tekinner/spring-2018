package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Randoms.Randoms;

public class OrderApp {

	public static void main(String[] args) throws InterruptedException {
		Randoms r = new Randoms();
		
		System.setProperty("webdriver.chrome.driver","/Users/ertekinkoc/Selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.findElement(By.cssSelector("a[href='Process.aspx']")).click();

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(r.randomZip());

		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John "+r.randomMiddleName()+" Smith");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any Street");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anycity");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("VA");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(r.randomZip());
		
		switch(r.cardSelect()) {
		case 1:
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_0']")).click(); 
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(r.cardNumber(4, 15)); 
			break;

		case 2:	
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_1']")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(r.cardNumber(4, 15)); 
			break;
		case 3:
			driver.findElement(By.cssSelector("label[for='ctl00_MainContent_fmwOrder_cardList_2']")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(r.cardNumber(3, 14)); 
			break;
		}
		
		Thread.sleep(2000);
		driver.close();
		
		
	}

}
