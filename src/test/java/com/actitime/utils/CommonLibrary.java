package com.actitime.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.actitime.base.BaseClass;

public class CommonLibrary extends BaseClass{
	
	
	
	
	public static void launchAndLoginToActitime() throws IOException
	{
		
		
		getWebElementFromPageAndElementNames("Login","UserName_EditBox").
		sendKeys(testDataMap.get("Login"+"$"+"UserName_EditBox"));	
		
		
		getWebElementFromPageAndElementNames("Login","Password_EditBox").
		//sendKeys(getTestDataFromExcel("Login", "Password_EditBox"));
		sendKeys(testDataMap.get("Login"+"$"+"Password_EditBox"));
		
		getWebElementFromPageAndElementNames("Login","Login_Button").click();
		
		
	}
	
	
	public static void launchAndLoginToActitime(String userName, String password) throws IOException
	{		
		
		getWebElementFromPageAndElementNames("Login","UserName_EditBox").
		sendKeys(userName);	
		
		
		getWebElementFromPageAndElementNames("Login","Password_EditBox").
		sendKeys(password);
		
		getWebElementFromPageAndElementNames("Login","Login_Button").click();
		
		
	}
	
	

}
