/*
 * THIS CODE MAKE A SEARCH ON DICE.COM BASED ON THE QUERIES IN THE LIST.TXT FILE
 * AND STORE THE SEARCH RESULST IN AN ARRALIST*/


package com.cybertek;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dice {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		ArrayList<String> list = new ArrayList<>();
		
		try (	FileReader fr = new FileReader("List.txt");
				BufferedReader br = new BufferedReader(fr);){
		String str = "";
		System.out.println(str);

		while((str = br.readLine())!=null) {

			driver.get("https://www.dice.com/");

			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(str);
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys("07072");
			driver.findElement(By.id("findTechJobs")).click();
			String count =driver.findElement(By.id("posiCountId")).getText();
			
			list.add(str+"-"+count);
//			driver.close();
			
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(list);

		Thread.sleep(3000);
		driver.quit();
		
	}

}