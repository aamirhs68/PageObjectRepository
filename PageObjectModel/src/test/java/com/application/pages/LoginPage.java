package com.application.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.common.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;

public class LoginPage extends BaseClass {
	
	@FindBy(xpath="//span[text()='Sign In']")
	private static WebElement btnSignIn;
	
	@FindBy(xpath="//input[@name='email']")
	private static WebElement txtEmail;
	
	@FindBy(xpath="//span[text()='Switch to Password']")
	private static WebElement btnSwitchToPassword;
	
	@FindBy(xpath="//input[@name='password']")
	private static WebElement txtPassword;
	
	@FindBy(xpath="//a/span[text()='Sign In']")
	private static WebElement btnSignOn;

	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) throws Exception
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login() throws Exception
	{
		Thread.sleep(3000);
		
		btnSignIn.click();
		Thread.sleep(2000);
		btnSwitchToPassword.click();
		Thread.sleep(5000);
		txtEmail.sendKeys(loginTestData.get("userName"));
		txtPassword.sendKeys(loginTestData.get("password"));
		Thread.sleep(3000);
		
		btnSignOn.click();
		
		logger.pass("Login Page", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		
	}

}
