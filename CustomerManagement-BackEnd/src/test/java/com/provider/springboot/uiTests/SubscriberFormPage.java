package com.provider.springboot.uiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SubscriberFormPage {

	
	WebDriver driver;

	
	public SubscriberFormPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how=How.ID,using="subscriberForm") 
	@CacheLookup
	WebElement subscriberForm;
	
	@FindBy(how=How.ID,using="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(how=How.ID,using="address")
	@CacheLookup
	WebElement address;

	@FindBy(how=How.ID,using="email")
	@CacheLookup
	WebElement email;
	
	@FindBy(how=How.ID,using="submitButton")
	@CacheLookup
	WebElement submitButton;
	
	@FindBy(how=How.ID, using="list")
	@CacheLookup
	WebElement list;
	
	@FindBy(how=How.ID, using="subscriberId")
	@CacheLookup
  	WebElement subscriberId;
	
	@FindBy(how=How.ID, using="subscriberUsername")
	@CacheLookup
	WebElement subscriberUsername;
	
	@FindBy(how=How.ID, using="subscriberAddress")
	@CacheLookup
	WebElement subscriberAddress;
	
	@FindBy(how=How.ID, using="subscriberEmail")
	@CacheLookup
	WebElement subscriberEmail;
	
	@FindBy(how=How.ID, using="resetButton")
	@CacheLookup
	WebElement resetButton;
	
	@FindBy(how=How.ID, using="editButton")
	@CacheLookup
	WebElement editButton;
	
	@FindBy(how=How.ID, using="deleteButton")
	@CacheLookup
	WebElement deleteButton;
	
	
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
	
	public void clear() {
		username.clear();
		address.clear();
		email.clear();
	}
	
	public void submit() {
		submitButton.click();
	}
	
}
