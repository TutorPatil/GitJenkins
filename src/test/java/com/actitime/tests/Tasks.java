package com.actitime.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.base.BaseClass;
import com.actitime.utils.CommonLibrary;

public class Tasks extends BaseClass{
	
	
	@Test
	public static void login_001() throws IOException
	{		
		writeLogs("*********starting the test case**********");
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
	
		//Assert.assertFalse(result, "Login_001 Failed");
		Assert.assertTrue(result, "Login_001 Failed");	
		
		
	}

}
