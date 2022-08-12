package com.provider.springboot.seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWebDriverDemo {
	
	 
	public static void main(String[] args) throws InterruptedException {//avoids interrupting the execution during the process
	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\roger.richards\\Desktop\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
		SearchBox.sendKeys("jbl earphones");
		WebElement SearchIcon = driver.findElement(By.id("nav-search-submit-button"));
		SearchIcon.click();
		WebElement CheckBox = driver.findElement(By.id("p_89/boAt"));
		CheckBox.click();
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,4000)");
		Thread.sleep(4000);
		driver.quit();
		
	}
}
