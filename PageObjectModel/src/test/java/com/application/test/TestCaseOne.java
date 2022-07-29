package com.application.test;
import org.testng.annotations.Test;

import com.application.common.BaseClass;
import com.application.pages.HomePage;
import com.application.pages.LoginPage;

public class TestCaseOne extends BaseClass {
	
	
	@Test
	
	public void testcasevalidateLogin() throws Exception
	{
		BaseClass.invokeBrowser();
		new LoginPage(getDriver()).login();
		new HomePage(getDriver()).clickOnHomeButton();
		
	}

	
}
