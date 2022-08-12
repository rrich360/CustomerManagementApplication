package com.provider.springboot.uiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

	
	 WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how=How.ID,using="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(how=How.ID,using="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.ID,using="loginButton")
	@CacheLookup
	WebElement loginButton;

	@FindBy(how=How.ID,using="errorMessage")
	@CacheLookup
	WebElement errorMessage;
	
	@FindBy(how=How.ID,using="welcomeMessage")
	@CacheLookup
	WebElement welcomeMessage;
	
	@FindBy(how=How.ID, using="successMessage")
	@CacheLookup
	WebElement successMessage;
	
	public void loginCustomerTest(String user, String pass){
		try {
			username.sendKeys(user);
			wait(3000);//milli
			password.sendKeys(pass);
			wait(3000);
			loginButton.click();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
