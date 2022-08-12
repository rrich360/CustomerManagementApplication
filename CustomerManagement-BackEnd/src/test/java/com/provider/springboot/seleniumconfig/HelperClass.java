package com.provider.springboot.seleniumconfig;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class HelperClass {

	
	public static WebDriver driver;
	BrowserFactory obj1;
	
	public HelperClass() {
		
	}
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@BeforeClass
	public void beforeMethodClass() {
		System.out.println("in @BeforeMethod");
		HelperClass.driver = BrowserFactory.getDriver("chrome");
	}
	
	@AfterMethod
	public void close()//closes db connection
	{
		//this.driver.close();
	}
	
	@AfterSuite
	public void afterSuite() throws IOException{
		
		driver.close();
		driver.quit();
	}
}
