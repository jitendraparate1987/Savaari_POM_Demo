package com.savaari.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	
	Workbook workbook = null;
	
	public HashMap getExcelData(String filename)
	{
		HashMap<String, String> map = new HashMap<String,String>();
		String key;
		String value;
		
		String filepath = System.getProperty("user.dir")+"\\input\\" + filename;
				
		try {workbook = new XSSFWorkbook(new FileInputStream(new File(filepath)));}
		catch (Exception e) {	e.printStackTrace();}
		
		Sheet sheet = workbook.getSheet("TestData");
		
		int rownum = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		for(int i=1;i<=rownum;i++)
		{
			Row row = sheet.getRow(i);
			
			map.put(row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue());
		}
		
		return map;
		
	}
	
}
