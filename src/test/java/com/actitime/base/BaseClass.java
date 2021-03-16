package com.actitime.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.io.Files;

public class BaseClass implements ITestListener{
	
	public static WebDriver driver = null;	
	public static Logger logger = Logger.getLogger("BaseClass");
	
	public static Map<String,String> locatorMap = new HashMap<String,String>();
	public static Map<String,String> testDataMap = new HashMap<String,String>();
	
	
	public static void writeLogs(String msg)
	{		
		logger.info(msg);
	}
	
	public static void writeErrorLogs(Throwable t)
	{
		String s = Arrays.toString(t.getStackTrace());		
		String s1 = s.replaceAll(",", "\n");		
		logger.error(s1);
		//elogger.log(Status.FAIL, "The test case Failed the exception captured is "+s1);
		
	}
	
	
	public static void getAndStoreLocatorData() throws IOException {
		
		String xpath = "";
		

		File f= new File("./src/test/data/locatordata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet2");
		
		int rows = ws.getLastRowNum();
		//rows= rows-1;
		
		System.out.println(rows);

		for (int x = 1; x <= rows; x++) {			
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();	
			xpath = ws.getRow(x).getCell(2).getStringCellValue();
			locatorMap.put(page+"$"+element, xpath);
				
			}	
		writeLogs("Locator hash Map ===" + locatorMap);

		wb.close();
		
	}
	
	
	public static void getAndStoreTestData() throws IOException {
		String data = "";

		File f= new File("./src/test/data/testdata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet2");
		
		int rows = ws.getLastRowNum();
		//rows= rows-1;
		
		System.out.println(rows);

		for (int x = 1; x <= rows; x++) {

			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();			
			data = ws.getRow(x).getCell(2).getStringCellValue();
			testDataMap.put(page+"$"+element, data);
			

		}
		
		writeLogs("TestData hash Map ===" + testDataMap);

		wb.close();
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public static void launchApplication() throws IOException {
		
		String browser = getConfigData("browser");		
		String url = getConfigData("url");
		
		if ( browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/tools/chromedriver.exe");
			driver = new ChromeDriver();
		
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./src/test/tools/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(url);		
		driver.manage().window().maximize();
		
		// implicit wait		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static By getByClassRefPageAndLocatorName(String pageName,String elementName) throws IOException
	{
		By b = null;	
		
		//b = By.xpath(getLocatorDataFromExcel(pageName,elementName));	
		b = By.xpath(locatorMap.get(pageName+"$"+elementName));
		
		return b;
	}
	
	public static WebElement getWebElementFromPageAndElementNames(String pageName, String ElementName) throws IOException
	{
		By b = getByClassRefPageAndLocatorName(pageName,ElementName);
		WebElement elemn = driver.findElement(b);
		
		return elemn;
		
	}
	
	
	public static String getConfigData(String propName) throws IOException
	{
		String propValue = null;
		
		File f = new File("./src/test/data/config.properties");
		InputStream fio = new FileInputStream(f);		
		Properties prop = new Properties();
		prop.load(fio);		
		propValue = prop.getProperty(propName);
		
		return propValue;
	}
	
	
	public static String getLocatorDataFromExcel(String pageName, String elementName) throws IOException
	{
		String locator = "";
		
		File f= new File("./src/test/data/locatordata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		for(int x=1; x<=rows;x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if (page.equalsIgnoreCase(pageName) && (element.equalsIgnoreCase(elementName)))
			{
				locator = ws.getRow(x).getCell(2).getStringCellValue();
				break;
			}
				
		}		
		wb.close();
		
		return locator;
		
		
	}
	
	
	public static String getTestDataFromExcel(String pageName, String elementName) throws IOException
	{
		String testdata = "";
		
		File f= new File("./src/test/data/testdata.xlsx");		
		FileInputStream fio = new FileInputStream(f);
		
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rows = ws.getLastRowNum();
		
		for(int x=1; x<=rows;x++)
		{
			String page = ws.getRow(x).getCell(0).getStringCellValue();
			String element = ws.getRow(x).getCell(1).getStringCellValue();
			
			if (page.equalsIgnoreCase(pageName) && (element.equalsIgnoreCase(elementName)))
			{
				testdata = ws.getRow(x).getCell(2).getStringCellValue();
				break;
			}
				
		}		
		wb.close();
		
		return testdata;
		
		
	}
	
	
	public static void  writeResultsToFile(String testCaseName, String status) throws IOException
	{
		File f = new File("./src/test/results/results.txt");
		FileWriter fw = new FileWriter(f,true);
		
		fw.write(testCaseName+"-----"+status+" \n");
		
		fw.flush();
		fw.close();
		
		
	}
	
	
	public static void capturescreenShot(String fileName) throws IOException
	{
		
		TakesScreenshot ts = (TakesScreenshot)driver;		
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./src/test/results/screenshots/"+fileName+".png");
		Files.copy(src, dest);
		
		
		
	}
	
	@AfterMethod(alwaysRun=true)
	public static void closeBrowser()
	{
		driver.quit();
	}
	
	@BeforeTest
	public static void beforeTest()
	{
		writeLogs("This method will run before every Test");
	}
	
	
	public static void afterTest()
	{
		writeLogs("This method will run after every Test");
	}
	
	@BeforeClass
	public static void beforeclass()
	{
		writeLogs("This method will run before every class");
	}
	
	@AfterClass
	public static void afterClass()
	{
		writeLogs("This method will run after every class");
	}
	
	
	// TestNg  Listener methods implementation


	public void onFinish(ITestContext arg0) {
		writeLogs("This method should run at the end of tthe execution of the entire run");
		
	}

	
	public void onStart(ITestContext arg0) {
		try {
			getAndStoreLocatorData();
			getAndStoreTestData();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
		writeLogs("This method should run in the beginning of the execution of the entire run");
		
		File f = new File("./src/test/results/results.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Starting to write the results for this run\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailure(ITestResult arg0) {
		
		writeLogs("The test case by name "+arg0.getName()+" has Failed!!");
		try {
			capturescreenShot(arg0.getName());
			writeResultsToFile(arg0.getName(), "Fail");
			Throwable t = arg0.getThrowable();
			writeErrorLogs(t);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	
	public void onTestSkipped(ITestResult arg0) {
		writeLogs("The test case by name "+arg0.getName()+" is skipped!!");
		
	}


	public void onTestStart(ITestResult arg0) {
		
		writeLogs("**********The test case by name "+arg0.getName()+" is started running*********");
	}

	
	public void onTestSuccess(ITestResult arg0) {
		writeLogs("The test case by name "+arg0.getName()+" has Passed");	
			
			try {
				writeResultsToFile(arg0.getName(), "Pass");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		
	}
	
	
	
	

}
