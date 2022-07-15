package com.provider.springboot.selenium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTesting {
	
	private WebDriver driver;

	private static final String LOGIN_URL = "http://localhost:8080/";
		
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="loginButton")
	private WebElement loginButton;

	@FindBy(id="errorMessage")
	private WebElement errorMessage;
	
	@FindBy(id="welcomeMessage")
	private WebElement welcomeMessage;
	
	@FindBy(id="successMessage")
	private WebElement successMessage;
	
	@BeforeSuite
	public void setup() {
		// create driver
		driver = new SeleniumWebDriverConfig().ChromeWebDriver();
		PageFactory.initElements(driver, this); //helps selenium find web elements
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@BeforeTest
	public void beforeEach() {
		driver.navigate().to(LOGIN_URL);
	}
	
	@Test
	public void givenLoginPage_whenValidUsernameAndPassword_thenNavigateToHomePage() {
		doLogin("rrichar1", "hearts246");
		String expectedUrl  = LOGIN_URL;
		String currentURL = driver.getCurrentUrl();
		assertEquals(currentURL, expectedUrl, "Current URL is not the same as expected URL");
		
		assertTrue(welcomeMessage.isDisplayed(), "Welcome message is not displayed");
		
		String expectedMessage = "rrichar1, you have successfully logged in!";
		String actualMessage = successMessage.getText();
		assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected message");
	}
	
	
	@Test
	public void givenLoginPage_whenValidUsernameInvalidPassword_thenErrorOnLoginPage() {	
		doLogin("rrichar1", "invalid");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeMessage"), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent("successMessage"), "Success login message is displayed incorrectly.");
		assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	
	@Test
	public void givenLoginPage_whenInvalidUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin("", "hearts246");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeMessage"), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent("successMessage"), "Success login message is displayed incorrectly.");
		assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	
	@Test
	public void givenLoginPage_whenValidUsernameAndNullPassword_thenErrorOnLoginPage() {	
		doLogin("rrichar1", null);
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeMessage"), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent("successMessage"), "Success login message is displayed incorrectly.");
		assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	
	@Test
	public void givenLoginPage_whenNullUsernameAndValidPassword_thenErrorOnLoginPage() {	
		doLogin(null, "hearts246");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeMessage"), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent("successMessage"), "Success login message is displayed incorrectly.");
		assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	
	@Test
	public void givenLoginPage_whenNullUsernameAndNullPassword_thenErrorOnLoginPage() {	
		doLogin(null, null);
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeMessage"), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent("successMessage"), "Success login message is displayed incorrectly.");
		assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	
	private void doLogin(String testUsername, String testPassword) {
		
		if(testUsername != null) {
			username.sendKeys(testUsername);
		}
		if(testPassword != null) {
			password.sendKeys(testPassword);
		}
		// click login button
		loginButton.click();
		
	}
	
	private Boolean isElementPresent(String elementId) {
		return driver.findElements(By.id(elementId)).size() > 0;
	}
	
}
