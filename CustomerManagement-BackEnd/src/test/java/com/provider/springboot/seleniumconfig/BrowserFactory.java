package com.provider.springboot.seleniumconfig;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	
	public static WebDriver driver;
	
	public BrowserFactory() {
		
	}
	
	public static WebDriver getDriver() throws InterruptedException{//avoids interruption during execution
		if(driver==null) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable notifications");//stop potential notifications during executions
			options.setPageLoadStrategy(PageLoadStrategy.NONE);// so page does not load while execution
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\roger.richards\\Desktop\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));//
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
			//implicitlyWait method that uses unit of time as param is deprecated
			//pageLoadTimeout method that uses unit of time as param is deprecated
		}
		return driver;
	}
	
	public static WebDriver getDriver(String browserName) {
		if(driver==null) {
			if(browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\roger.richards\\Desktop\\geckodriver-v0.31.0-win64\\geckodriver.exe");
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
			}else if(browserName.equalsIgnoreCase("chrome")) {
				System.out.println("in chrome");
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\roger.richards\\Desktop\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));	
			}else if(browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "C:\\Users\\roger.richards\\Desktop\\IEDriverServer_x64_4.3.0\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));	
			}
		}
			return driver;
		}
	}

