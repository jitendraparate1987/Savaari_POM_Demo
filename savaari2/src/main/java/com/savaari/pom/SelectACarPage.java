package com.savaari.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.savaari.utils.DateAndTimeUtils;


public class SelectACarPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public Robot robot;
	DateAndTimeUtils TimeUtils;
	
	public SelectACarPage(WebDriver driver) throws Exception
	{
	  this.driver = driver;
	  this.wait = new WebDriverWait(this.driver, 180);
	  robot = new Robot();
	  TimeUtils = new DateAndTimeUtils();
	}
	
	// Method to select a given car with Costliest Fare
	
	public HashMap<String,String> SelectGivenCostliestFareCar(String CarBrand)
	{
		  HashMap<String,String> map = new HashMap<String,String>();
		  List<WebElement> Cars = driver.findElements(By.tagName("h5"));
		  List<WebElement> CarPrice = driver.findElements(By.className("price_ttl"));
		  
		  int count=0;
		  int getMaxCarValueIndex=0;
		  int tempCarRent =0;
		  for(WebElement ShowCars : Cars)
		  {  
			  if(ShowCars.getText().contains(CarBrand))
			  {
			  //System.out.print(ShowCars.getText()+"||");
			  
				  if(tempCarRent<=Integer.parseInt(CarPrice.get(count).getText().replace("Rs. ", "").replace(",", "")))
				  {
					  getMaxCarValueIndex= count;
					  tempCarRent=Integer.parseInt(CarPrice.get(count).getText().replace("Rs. ", "").replace(",", ""));
				  }
			  
			  }
			  count++;
			  	  
		  }
		   
		  // Reset count to 0 for reusablility
		  count = 0;
		  
		  List<WebElement> getContinueButton = driver.findElements(By.className("btn-select-car"));
		  
		  for(WebElement selectContinueButton : getContinueButton)
		  {	  if(selectContinueButton.isDisplayed())
			  {
				  if(selectContinueButton.getAttribute("value").equals("Continue"))
				  {  if(count == getMaxCarValueIndex)
					  {   
					      map.put("CarSelected", Cars.get(count).getText());
					      map.put("Fare",CarPrice.get(count).getText());
					       selectContinueButton.sendKeys(Keys.ENTER);break;
					  }
				  }
				  count++;
			  }
				  
		  }
		  
		  return map;
		
		
	} // End of method
	
	

}
