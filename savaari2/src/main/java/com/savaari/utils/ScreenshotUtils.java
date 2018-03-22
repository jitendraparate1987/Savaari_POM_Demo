package com.savaari.utils;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScreenshotUtils {

	
	public WebDriver driver;
	public WebDriverWait wait;
	DateAndTimeUtils TimeUtils;
	
	public ScreenshotUtils(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void captureScreenshot(String classname, String methodname, String Browsername) throws Exception
	{
		String filename = System.getProperty("user.dir")+"\\screenshots\\"+ Browsername + "_" + classname + "_" + methodname +".jpg"; 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File(filename));
                      
	}

	
	
}
