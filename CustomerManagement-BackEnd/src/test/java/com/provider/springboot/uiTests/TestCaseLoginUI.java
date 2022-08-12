package com.provider.springboot.uiTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.provider.springboot.seleniumconfig.HelperClass;

public class TestCaseLoginUI extends HelperClass{

	private static final String LOGIN_URL = "http://localhost:8080/";
	
	public TestCaseLoginUI() {}
	
	
	
	@Test
	public void login() {
		
		try {
			System.out.println("Logging in webApp");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest("rrichar1", "hearts246");
			
			assertTrue(loginPage.welcomeMessage.isDisplayed(), "Welcome message is not displayed");
			String expectedMessage = "rrichar1, you have successfully logged in!";
			String actualMessage = loginPage.successMessage.getText();
			assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected message");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void givenLoginPage_whenValidUsernameInvalidPassword_thenErrorOnLoginPage() {
		
		try {
			System.out.println("Logging in webApp for the second test");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest("rrichar1", "invalid");
		
			String expectedUrl = LOGIN_URL;
			String currentUrl = driver.getCurrentUrl();
			assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
			assertFalse(isElementPresent(loginPage.welcomeMessage), "Welcome message is displayed incorrectly.");
			assertFalse(isElementPresent(loginPage.successMessage), "Success login message is displayed incorrectly.");
			assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed");
		
			String expectedMsg = "Invalid details";
			String actualMsg = loginPage.errorMessage.getText();
			assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void givenLoginPage_whenInvalidUsernameValidPassword_thenErrorOnLoginPage() {	
		try {
			System.out.println("Logging in webApp for the third test");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest("", "hearts246");
			
			String expectedUrl = LOGIN_URL;
			String currentUrl = driver.getCurrentUrl();
			assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent(loginPage.welcomeMessage), "Welcome message is displayed incorrectly."); //check web elements
		assertFalse(isElementPresent(loginPage.successMessage), "Success login message is displayed incorrectly.");
		assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = loginPage.errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void givenLoginPage_whenValidUsernameAndNullPassword_thenErrorOnLoginPage() {	
		
		try {
			System.out.println("Logging in webApp for the fourth test");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest("rrichar1", null);
			
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent(loginPage.welcomeMessage), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent(loginPage.successMessage), "Success login message is displayed incorrectly.");
		assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = loginPage.errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameAndValidPassword_thenErrorOnLoginPage() {
		
		try {
			System.out.println("Logging in webApp for the fourth test");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest(null, "hearts246");
			
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent(loginPage.welcomeMessage), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent(loginPage.successMessage), "Success login message is displayed incorrectly.");
		assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = loginPage.errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameAndNullPassword_thenErrorOnLoginPage() {
		
		try {
			System.out.println("Logging in webApp for the fourth test");
			driver.get(LOGIN_URL);
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginCustomerTest(null, null);
			
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent(loginPage.welcomeMessage), "Welcome message is displayed incorrectly.");
		assertFalse(isElementPresent(loginPage.successMessage), "Success login message is displayed incorrectly.");
		assertTrue(loginPage.errorMessage.isDisplayed(), "Error message is not displayed");
		
		String expectedMsg = "Invalid details";
		String actualMsg = loginPage.errorMessage.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	} catch(Exception e) {
		e.printStackTrace();
		}
	}
	
	
	
	private Boolean isElementPresent(WebElement welcomeMessage) {
		return driver.findElements((By) welcomeMessage).size() > 0;
	}
	
	
}
