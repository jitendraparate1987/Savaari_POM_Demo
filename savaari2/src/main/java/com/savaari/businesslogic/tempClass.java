package com.savaari.businesslogic;

import java.util.HashMap;
import com.savaari.utils.ExcelUtils;

public class tempClass {

	public static void main(String arg[]) throws Exception
	{
		HashMap<String,String> map = new HashMap<String,String>();
		ExcelUtils Excel = new ExcelUtils();
		
		map = Excel.getExcelData("InputFile.xlsx");
		
		System.out.println(map);
		System.out.println(map.get("url"));
		System.out.println(map.get("Destination"));
	}
}
