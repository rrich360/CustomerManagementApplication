package com.provider.springboot.selenium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SubscriberFormTest{
	private static final String BASE_URL =  "http://localhost:8080/";
	
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(id="subscriberForm")
	private WebElement subscriberForm;
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="address")
	private WebElement address;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(id="submitButton")
	private WebElement submitButton;
	
	@FindBy(id="list")
   	private WebElement list;
	
	@FindBy(id="subscriberId")
  	private WebElement subscriberId;
	
	@FindBy(id="subscriberUsername")
	private WebElement subscriberUsername;
	
	@FindBy(id="subscriberAddress")
	private WebElement subscriberAddress;
	
	@FindBy(id="subscriberEmail")
	private WebElement subscriberEmail;
	
	@FindBy(id="resetButton")
	private WebElement resetButton;
	
	@FindBy(id="editButton")
	private WebElement editButton;
	
	@FindBy(id="deleteButton")
	private WebElement deleteButton;
	
	@BeforeSuite
	public void setup() {
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
		driver.navigate().to(BASE_URL + "UserManagement");
		wait = new WebDriverWait(driver, Duration.ofSeconds(500));
	}
	
	@Test
	public void givenSubscriberPage_whenAllInputFieldsValid_thenAddSubscriberToList() {
		enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		String expectedUrl = BASE_URL + "UserManagement";
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Current URL is not the same as expected URL");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		assertTrue(list.isDisplayed(), "Saved user does not appear on list");
		assertTrue(subscriberUsername.getText().equals("Andrea Sullivan"), "Saved user does not have correct username");
		assertFalse(isElementEnabled("submitButton", submitButton));
		deleteButton.click();
	}
	
	@Test
	public void givenSubscriberPage_whenEmptyUsername_AddButtonIsDisabled() {
		clear();
		enterFormData("", "Atlanta, GA","Andrea.Sully09@gmail.com");
		assertFalse(isElementEnabled("submitButton", submitButton));
	}
	
	@Test
	public void givenSubscriberPage_whenEmptyEmail_AddButtonIsDisabled() {
		clear();
		enterFormData("Andrea Sullivan","Atlanta, GA", null);
		assertFalse(isElementEnabled("submitButton", submitButton));
	}
	
	@Test
	public void givenPreAddedSubscriber_whenUsernameIsChanged_thenSubscriberIsUpdated() {
		clear();
		enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("editButton")));

		editButton.click();
		clear();
		enterFormData("drea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subscriberUsername")));
		assertTrue(subscriberUsername.getText().equals("drea Sullivan"),"Saved user does not have updated username");
		deleteButton.click();
	}
	
	@Test
	public void givenPreAddedSubscriber_whenAddressIsChanged_thenSubscriberIsUpdated() {
		clear();
		enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		
		editButton.click();
		clear();
		enterFormData("Andrea Sullivan","LA, Cali","Andrea.Sully09@gmail.com");
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subscriberAddress")));
		assertTrue(subscriberAddress.getText().equals("LA, Cali"), "Saved user does not have updated address");
		deleteButton.click();
	}
	
	@Test
	public void givenPreAddedSubscriber_whenEmailIsChanged_thenSubscriberIsUpdated() {
		clear();
		enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
		assertTrue(isElementEnabled("submitButton", submitButton));
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
		
		editButton.click();
		clear();
		enterFormData("Andrea Sullivan","Atlanta, GA","AndreaSul09@gmail.com");
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subscriberEmail")));
		assertTrue(subscriberEmail.getText().equals("AndreaSul09@gmail.com"), "Saved user does not have updated email");
		deleteButton.click();
	}
	
	public void enterFormData(String testUsername,String testAddress, String testEmail) {
		if (subscriberForm.isDisplayed()) {
			if (testUsername != null) {
				username.sendKeys(testUsername);
			}
			if (testAddress != null) {
				address.sendKeys(testAddress);
			}
			if (testEmail != null) {
				email.sendKeys(testEmail);
			}
		}
	}
	
	public void submit() {
		submitButton.click();
	}
	
	public void clear() {
		username.clear();
		address.clear();
		email.clear();
	}
	
	private boolean isElementEnabled(String elementId, WebElement webElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
        return webElement.isEnabled();
	} 
}
