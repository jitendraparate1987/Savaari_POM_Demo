package com.savaari.businesslogic;

import org.testng.annotations.Test;

import com.savaari.pom.BookingSummary;
import com.savaari.pom.HomePage;
import com.savaari.pom.SelectACarPage;
import com.savaari.pom.SelectACarPage2;
import com.savaari.utils.DateAndTimeUtils;
import com.savaari.utils.ExcelUtils;
import com.savaari.utils.ScreenshotUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookTicket  {
  
	WebDriver driver;
	WebDriverWait wait;
	Properties property;
	HomePage HP;
    SelectACarPage SelectCar;
    SelectACarPage2 SelectCar2;
    BookingSummary ValidateBooking;
    DateAndTimeUtils TimeUtils;
    ScreenshotUtils screenshot;
    ExcelUtils ExcelUtil;
    HashMap<String,String> map;
    HashMap<String,String> output;
    Capabilities caps;
	
    @Parameters({"browser"})
    @BeforeClass
    public void InitiateDriver(String browser) throws Exception {
	  	  
    // load data from input file
    	
	  property = new Properties();
	  property.load(new FileInputStream(new File(System.getProperty("user.dir") + "\\config\\config.properties")));
	  
	 
	  // configure browser 
	  
	  if(browser.equalsIgnoreCase("InternetExplorer"))
	  {
		 System.setProperty(property.getProperty("IEDriver"),property.getProperty("IEDriverPath"));
		 driver = new InternetExplorerDriver();
	  }
	  else if(browser.equalsIgnoreCase("Chrome"))
	  {   
		  ChromeOptions options = new ChromeOptions();
		  
		  options.addArguments("disable-infobars");
		  options.addArguments("disable-extensions");
		  options.addArguments("disable-notifications");
		  

		  System.setProperty(property.getProperty("ChromeDriver"),property.getProperty("ChromeDriverPath"));
	     driver = new ChromeDriver(options);
	  }
	  
	  else if(browser.equals("Firefox"))
	  {
          DesiredCapabilities capabilities = new DesiredCapabilities();
          driver = new FirefoxDriver();
	  }
	  
	  else {
	    System.out.println("Appropriate browser is not selected. Script would terminate");System.exit(0);
	  }
	  
	
	  caps = ((RemoteWebDriver) driver).getCapabilities();
	  System.out.println(caps.getBrowserName());
	  
	  // Initiate objects for POM Files. These needs to be invoked only when needed.
	  
	  HP = new HomePage(driver);
	  SelectCar2 = new SelectACarPage2(driver);
	  ValidateBooking = new BookingSummary(driver);
	  
	 
	  // Invoke objects for Utilities
	  
	  TimeUtils = new DateAndTimeUtils();
	  screenshot = new ScreenshotUtils(driver);
	  ExcelUtil = new ExcelUtils();
	  
	  
	  // Get data from input file
	  
	  map =  ExcelUtil.getExcelData(property.getProperty("InputFileName"));
	 
	  
	  // launch browser url
	  
	  driver.get(map.get("url"));
	  driver.manage().window().maximize();
	  
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  Thread.sleep(10000);
	  
	  // close the browser
	   
	  driver.close();
	  
  }

  @Test(enabled = true)
  public void TC001_SelectACar() throws Exception {	  
	  
	  HP.selectPickUpLocation(map.get("Source"));
	  HP.selectDropLocation(map.get("Destination"));
	  HP.selectStartingOnDate(TimeUtils.getTodaysDate());
	  HP.selectPickupTime();
	  HP.ClickOnSelectACar();
	 
	  Thread.sleep(9000); 
	  String val = "";
	 
	  List<WebElement> checkpoint = driver.findElements(By.className("columns"));
	 for(WebElement checkpointvalidation : checkpoint)
	 {
		 if(checkpointvalidation.getText().equals("Showing cab prices for"))
		 {
			 val=checkpointvalidation.getText();
		 }
	 }
	  Assert.assertEquals(driver.findElement(By.className("columns")).getText(),val);
	  
  }
  
  @Test(dependsOnMethods = "TC001_SelectACar", enabled = true)
  public void TC002_SelectGivenCarWithHighFare()
  {  
	  output = SelectCar2.selectCostliestCar(map.get("CarBrand"));
	  
	  try {	Thread.sleep(8000);} catch (Exception e) { e.printStackTrace();}
	  
	  if(output!=null)
		  Assert.assertTrue(true);
	  else
		  Assert.assertTrue(false);
	 
  }
  
  @Test(dependsOnMethods = "TC002_SelectGivenCarWithHighFare", enabled = true)
  public void TC003_ValidateBookingSummary()
  {
	 Assert.assertTrue(ValidateBooking.validateBookingSummary(output.get("CarName"),output.get("CarFare"))); 
  }

  
  @AfterMethod
  public void afterMethod(ITestResult result) throws Exception {
   
	   if(result.getStatus() == ITestResult.FAILURE)
	   {
		  screenshot.captureScreenshot(this.getClass().getSimpleName(), result.getMethod().getMethodName(),caps.getBrowserName());
		  
	   }
	   
  }
}
