package com.actitime.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.actitime.base.BaseClass;
import com.actitime.utils.CommonLibrary;

public class Login extends BaseClass{
	
	
	//@Test(groups = { "smoke", "regression","login_001" })	
	
	@Test
	public static void login_001() throws IOException
	{		
		writeLogs("*********starting the test case**********");
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
	
		//Assert.assertFalse(result, "Login_001 Failed");
		Assert.assertTrue(result, "Login_001 Failed");	
		
		
	}
	
	
	@Test
	public static void login_002() throws IOException
	{		
		
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
	
		//Assert.assertFalse(result, "Login_001 Failed");
		Assert.assertFalse(result, "Login_002 Failed");	
		
		
	}
	
	//@Test
	public static void login_003() throws IOException
	{		
		
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
	
		//Assert.assertFalse(result, "Login_001 Failed");
		Assert.assertTrue(result, "Login_001 Failed");	
		
		
	}
	
	
	
	//@Test
	public static void login_004() throws IOException
	{		
		
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
	
		//Assert.assertFalse(result, "Login_001 Failed");
		Assert.assertTrue(result, "Login_001 Failed");	
		
		
	}
	
	
	
	
	
	
	
	
	
	
		
	//@Test(dependsOnMethods = { "login_001" })
	//@Test(groups = { "smoke","login_002" })
	
	//@Test(dataProvider = "logindata")
	
	//@Test(dataProvider = "logindata", dataProviderClass = com.actitime.dataproviders.DataProviders.class)
	public static void login_002(String user, String password) throws IOException
	{		
			
		CommonLibrary.launchAndLoginToActitime(user,password);		
		boolean result = getWebElementFromPageAndElementNames("Login","Error_Message").isDisplayed();
		
		if(result)
			writeResultsToFile("Login_002", "Pass");		
		else
		{
			writeResultsToFile("Login_002", "Fail");
			capturescreenShot("Login_002");
		}			
		closeBrowser();
		
	}	
	
	
	

}
