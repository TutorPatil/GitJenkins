package com.actitime.dataproviders;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "logindata")
	public static Object[][] supplyData() throws IOException
	{
		
		File file = new File("./data/testdata.xlsx");
		
		FileInputStream fio = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fio);

		XSSFSheet worksheet = workbook.getSheet("invaliddata");

		int rows = worksheet.getLastRowNum();
		
		System.out.println("+++++++++++++++"+rows);
		// rows = rows+1;
		
		Object[][] obj = new Object[rows+1][2];

		for (int x = 0; x <= rows; x++) {
			
			for(int y = 0; y < 2; y++)
			{
				obj[x][y] = worksheet.getRow(x).getCell(y).getStringCellValue();				
			}	 
		}
		workbook.close();
		return obj;
		
		/*
		obj[0][0] = "admin21";
		obj[0][1] = "manager212";
		
		obj[1][0] = "admin212";
		obj[1][1] = "manager1212";	
		
		*/
		//return obj;		
	}

}
