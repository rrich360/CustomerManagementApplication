package com.provider.springboot.uiTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.provider.springboot.seleniumconfig.HelperClass;

public class TestCaseSubscriberFormUI extends HelperClass{

	
	private static final String SUBSCRIBER_URL = "http://localhost:8080/UserManagement";
	
	WebDriverWait wait;

	
	 public TestCaseSubscriberFormUI() {}
	 
	 
	 @Test
		public void givenSubscriberPage_whenAllInputFieldsValid_thenAddSubscriberToList() {
		 
		 try { 
			 System.out.println("Logging in webApp for the first test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
				sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
				
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			String expectedUrl = SUBSCRIBER_URL;
			String currentUrl = driver.getCurrentUrl();
			assertEquals(currentUrl, expectedUrl, "Current URL is not the same as expected URL");
			
			assertFalse(sfPage.list.isDisplayed(), "Saved user does not appear on list");
			assertTrue(sfPage.subscriberUsername.getText().equals(""), "Saved user does not have correct username");
			assertFalse(isElementEnabled(sfPage.submitButton));
			
		 } catch(Exception e) {
				e.printStackTrace();
			}
		}
	  
	 
	 @Test
		public void givenSubscriberPage_whenAllInputFieldsValidTheSecondTime_thenAddSubscriberToList() {
		 
		 try { 
			 System.out.println("Logging in webApp for the second test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
				sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
				
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			String expectedUrl = SUBSCRIBER_URL;
			String currentUrl = driver.getCurrentUrl();
			assertEquals(currentUrl, expectedUrl, "Current URL is not the same as expected URL");
			
			wait.until(ExpectedConditions.presenceOfElementLocated((By)sfPage.list));
			assertTrue(sfPage.list.isDisplayed(), "Saved user does not appear on list");
			assertTrue(sfPage.subscriberUsername.getText().equals("Andrea Sullivan"), "Saved user does not have correct username");
			assertFalse(isElementEnabled(sfPage.submitButton));
			sfPage.deleteButton.click();
			
		 } catch(Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 
	 @Test
		public void givenSubscriberPage_whenEmptyUsername_AddButtonIsDisabled() {
		 try { 
			 System.out.println("Logging in webApp for the third test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
			sfPage.clear();
			sfPage.enterFormData("", "Atlanta, GA","Andrea.Sully09@gmail.com");
			
			
			assertFalse(isElementEnabled(sfPage.submitButton));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	 
	 @Test
		public void givenSubscriberPage_whenEmptyEmail_AddButtonIsDisabled() {
		 try {
			 System.out.println("Logging in webApp for the fourth test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","Atlanta, GA", null);
			assertFalse(isElementEnabled(sfPage.submitButton));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	 
	 @Test
		public void givenPreAddedSubscriber_whenUsernameIsChanged_thenSubscriberIsUpdated() {
		 
		 try {
			 System.out.println("Logging in webApp for the fifth test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			
			wait.until(ExpectedConditions.presenceOfElementLocated((By)sfPage.editButton));

			sfPage.editButton.click();
			sfPage.clear();
			sfPage.enterFormData("drea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			wait.until(ExpectedConditions.presenceOfElementLocated((By)sfPage.subscriberUsername));
			assertTrue(sfPage.subscriberUsername.getText().equals("drea Sullivan"),"Saved user does not have updated username");
			sfPage.deleteButton.click();
			
		} catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
		
	 
	 
	 @Test
		public void givenPreAddedSubscriber_whenAddressIsChanged_thenSubscriberIsUpdated() {
		 
		 try {
			 System.out.println("Logging in webApp for the sixth test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			wait.until(ExpectedConditions.presenceOfElementLocated((By)sfPage.list));
			
			sfPage.editButton.click();
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","LA, Cali","Andrea.Sully09@gmail.com");
			sfPage.submit();
			wait.until(ExpectedConditions.presenceOfElementLocated((By)sfPage.subscriberAddress));
			assertTrue(sfPage.subscriberAddress.getText().equals("LA, Cali"), "Saved user does not have updated address");
			sfPage.deleteButton.click();
			
		} catch(Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 
	 @Test
		public void givenPreAddedSubscriber_whenEmailIsChanged_thenSubscriberIsUpdated() {
		 try {
			 System.out.println("Logging in webApp for the sixth test");
				driver.get(SUBSCRIBER_URL);
				SubscriberFormPage sfPage = PageFactory.initElements(driver, SubscriberFormPage.class);
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","Andrea.Sully09@gmail.com");
			assertTrue(isElementEnabled(sfPage.submitButton));
			sfPage.submit();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list")));
			
			sfPage.editButton.click();
			sfPage.clear();
			sfPage.enterFormData("Andrea Sullivan","Atlanta, GA","AndreaSul09@gmail.com");
			sfPage.submit();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subscriberEmail")));
			assertTrue(sfPage.subscriberEmail.getText().equals("AndreaSul09@gmail.com"), "Saved user does not have updated email");
			sfPage.deleteButton.click();
			
		} catch(Exception e) {
				e.printStackTrace();
			}
		}
	 
	 
	 
	 
	 private boolean isElementEnabled(WebElement webElement) {
		 
			wait.until(ExpectedConditions.presenceOfElementLocated((By)(webElement)));
	        return webElement.isEnabled();
		} 
	
	
}
