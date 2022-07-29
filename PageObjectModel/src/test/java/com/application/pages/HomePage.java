package com.application.pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.common.BaseClass;
import com.aventstack.extentreports.MediaEntityBuilder;


public class HomePage extends BaseClass
{
    @FindBy(xpath="(//a[text()='CV Templates'])[1]")
    private static WebElement btnCVTemplates;
    
    WebDriver driver ;
    
    public HomePage(WebDriver driver) throws IOException
    {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    
    public void clickOnHomeButton() throws InterruptedException, IOException
    {
    	Thread.sleep(2000);
    	btnCVTemplates.click();
    	
    	logger.pass("Home Page after login", MediaEntityBuilder.createScreenCaptureFromPath("screenshot2.png").build());
		
    }

	}
