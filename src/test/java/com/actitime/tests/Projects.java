package com.actitime.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.base.BaseClass;
import com.actitime.utils.CommonLibrary;

public class Projects extends BaseClass{
	
	

	
	@Test
	public static void projects_001() throws IOException
	{		
		
		CommonLibrary.launchAndLoginToActitime();	
		boolean result = getWebElementFromPageAndElementNames("Home","Logout_Link").isDisplayed();
		
		Assert.assertTrue(result, "projects_001 Failed");	
		
		
	}
	

}
