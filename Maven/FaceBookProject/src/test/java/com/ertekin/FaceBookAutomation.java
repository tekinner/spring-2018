package com.ertekin;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FaceBookAutomation {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.findElement(By.name("firstname")).sendKeys("hello");
		driver.findElement(By.name("lastname")).sendKeys("world");
		driver.findElement(By.name("reg_email__")).sendKeys("ftnvi@nickrizos.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("world.12345*");
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("ftnvi@nickrizos.com");
		driver.findElement(By.xpath("//label[@class  ='_58mt'][contains(text(), 'Female')]")).click();
		
		driver.findElement(By.xpath("//option[@value ='9'][contains(text(), 'Sep')]")).click();
		driver.findElement(By.xpath("//option[@value ='9'][contains(text(), '9')]")).click();
		driver.findElement(By.xpath("//option[@value ='1998']")).click();
		driver.findElement(By.id("u_0_u")).click();
		
	//	<input type="email" class="inputtext" name="email" id="email" tabindex="1" data-testid="royal_email">
	//<button type="submit" class="_6j mvm _6wk _6wl _58mi _3ma _6o _6v" name="websubmit" id="u_0_u">Sign Up</button>
		//button
	
		
	}

}
