package com.savaari.pom;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectACarPage2 {
	
WebDriver driver;
WebDriverWait wait;
By listofcars = By.xpath("//h3[@class='price_ttl']/ancestor::div[@class='row']/descendant::h5");
By carfare = By.xpath("//h3[@class='price_ttl']");
By ContinueButton = By.xpath("//h3[@class='price_ttl']/ancestor::div[@class='row']/descendant::form[3]/input[@value='Continue' and @name='select_car']");


public SelectACarPage2(WebDriver driver){
	
 this.driver = driver;	
 this.wait= new WebDriverWait(this.driver, 180);

}

public HashMap<String,String> selectCostliestCar(String CarBrand){

   HashMap<String,String> map = new HashMap<String,String>();
   List<WebElement> CarFares = driver.findElements(carfare);
   List<WebElement> CarNames = driver.findElements(By.xpath("//h5"));
   
   int getmaxfare=0;
   String getMaxFareCarName="";
   String getMaxFareCarString="";
   int count=0;
   int getcountforcontinue=0;
   
  for(WebElement getCarFare : CarFares)
  {    
	  if((CarNames.get(count).getText().contains(CarBrand))&&(getmaxfare<=Integer.parseInt(getCarFare.getText().replace("Rs. ", "").replace(",", ""))))
	  {
		map.put("CarName",CarNames.get(count).getText());
		map.put("CarFare", getCarFare.getText());
		getmaxfare=Integer.parseInt(getCarFare.getText().replace("Rs. ", "").replace(",", ""));
		getcountforcontinue=count;
	  }
	  
	  count++;
	
  }
    driver.findElements(ContinueButton).get(getcountforcontinue).click();
   
   
   return map;

}


}


