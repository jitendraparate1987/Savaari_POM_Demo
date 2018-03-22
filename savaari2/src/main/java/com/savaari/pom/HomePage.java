package com.savaari.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.savaari.utils.DateAndTimeUtils;

public class HomePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Robot robot;
	DateAndTimeUtils TimeUtils;
	
	public HomePage(WebDriver driver) throws Exception
	{
	  this.driver = driver;
	  this.wait = new WebDriverWait(this.driver, 180);
	  robot = new Robot();
	  TimeUtils = new DateAndTimeUtils();
	}
	
	public void selectPickUpLocation(String pickuplocation) throws Exception
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("from_city")));
		driver.findElement(By.id("from_city")).sendKeys(pickuplocation);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void selectDropLocation(String droplocation) throws Exception
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("auto-city")));
		driver.findElement(By.className("auto-city")).sendKeys(droplocation);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void selectStartingOnDate(String todaysdate) throws Exception
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pickup_date")));
		driver.findElement(By.id("pickup_date")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("next")).click();
		List <WebElement> todaysdate1 = driver.findElements(By.className("day  "));
		
		for(WebElement selectTodaysDate : todaysdate1)
		{
			if(selectTodaysDate.getText().equals(todaysdate))
			{
				selectTodaysDate.click();break;
			}
		}
				
	}
	
	
	public void selectPickupTime() throws Exception
	{
	  // Select early time
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pickup_time")));
		Select select = new Select(driver.findElement(By.id("pickup_time")));
		Thread.sleep(2000);
		select.selectByIndex(1);
	}
	
	public void ClickOnSelectACar() throws Exception
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("go")));
		Thread.sleep(2000);
		driver.findElement(By.id("go")).click();
		Thread.sleep(10000);
	}
	
	
}
