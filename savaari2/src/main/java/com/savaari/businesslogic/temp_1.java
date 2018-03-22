package com.savaari.businesslogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.platform.unix.X11.Window;

public class temp_1 {

 static WebDriver driver = null;
 public static void main(String[] args) {
  // TODO Auto-generated method stub
 launchsite();
 checkpageloaded(); 
 searchresultflight();
 
 
 //*[@id='flightForm']/section[2]/div[3]/div[3]/button
 //*[@id='flightForm']/section[2]/div[3]/div[1]/h2
 
  
 
 }

public static void launchsite()
{
 System.out.println("Launching Browser");
 System.setProperty("webdriver.chrome.driver","C:\\Users\\paratji\\git\\savaari\\savaari\\chromedriver.exe");
 driver = new ChromeDriver();
 driver.get("https://www.cleartrip.com/");
 //driver.manage().window().maximize();
}

public static void checkpageloaded()
{
 WebDriverWait wait=new WebDriverWait(driver, 20);
 //String element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchBtn"))).getText();
 String element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='SearchForm']/h1"))).getText();
 System.out.println(element + " Page loaded Successfully");
}

public static void searchresultflight()
{
 driver.findElement(By.id("RoundTrip")).click();
 driver.findElement(By.xpath("//input[@id='FromTag']")).sendKeys("Pune, IN - Lohegaon (PNQ)");
 driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys("Jaipur, IN - Sanganeer (JAI)");
 driver.findElement(By.xpath("//input[@id='DepartDate']")).click();
 driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[5]/a")).click();
 driver.findElement(By.xpath("//input[@id='ReturnDate']")).click();
 driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[4]/td[3]/a")).click();
 
 Select adults = new Select(driver.findElement(By.id("Adults")));
 adults.selectByValue("2");
 
 Select infants = new Select(driver.findElement(By.id("Infants")));
 infants.selectByVisibleText("1"); 
 
 driver.findElement(By.xpath("//input[@id='SearchBtn']")).click(); 
 
 WebDriverWait mywait=new WebDriverWait(driver, 50);
 
 String b = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='flightForm']/section[2]/div[3]/div[1]/h2"))).getText();
 System.out.println(b + " Search Result Page loaded successfully");
 
 driver.findElement(By.xpath("//form[@id='flightForm']/section[2]/div[4]/div[1]/nav/ul/li[3]/div/label/table/tbody[1]/tr[1]/th[1]/input")).click();
 driver.findElement(By.xpath("//form[@id='flightForm']/section[2]/div[4]/div[2]/nav/ul/li[3]/div/label/table/tbody[1]/tr[1]/th[1]/input")).click();
 
 driver.findElement(By.xpath("//form[@id='flightForm']/section[2]/div[3]/div[3]/button")).click();
 
 System.out.println("Booking Initiated");

}

}
