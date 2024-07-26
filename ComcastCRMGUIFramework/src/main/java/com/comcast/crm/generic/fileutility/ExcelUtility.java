package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDtaFromExcel(String sheetName,int rouNum,int cellNum) throws Throwable {
		FileInputStream fis=new FileInputStream("./testData/testScripts.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 String data = wb.getSheet(sheetName).getRow(rouNum).getCell(cellNum).toString();
		 wb.close();
		return data;
		
		
	}
	public int getrowcount(String sheetName) throws Throwable {
		FileInputStream fis=new FileInputStream("./testData/testScripts.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
		
		
		
		
	}
	public void setDataIntoExcel(String sheetName,int rouNum,int cellNum,String data) throws Throwable {
		FileInputStream fis=new FileInputStream("./testData/testScripts.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		  wb.getSheet(sheetName).getRow(rouNum).createCell(cellNum);
		  FileOutputStream fos= new FileOutputStream("./testData/testScripts.xlsx");
		  wb.write(fos);
		  wb.close();
		
		
	}

}
