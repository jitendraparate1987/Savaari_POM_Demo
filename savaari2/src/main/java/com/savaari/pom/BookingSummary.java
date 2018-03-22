package com.savaari.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BookingSummary {

	WebDriver driver;
	WebDriverWait wait;
	
	public BookingSummary(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 180);
			
	}
	
	public boolean validateBookingSummary(String CarName, String Fare)
	{
		System.out.println(driver.findElements(By.className("caption-value-2")).get(7).getText());
		System.out.println(driver.findElement(By.id("fare_amount")).getText());
		
		if((driver.findElements(By.className("caption-value-2")).get(7).getText().contains(CarName))&&(driver.findElement(By.id("fare_amount")).getText().equals(Fare)))
		 return true;
		else
			return false;
			
	}
	
	
	//caption-value-2 - car name
	//fare_amount - fare
	
}
