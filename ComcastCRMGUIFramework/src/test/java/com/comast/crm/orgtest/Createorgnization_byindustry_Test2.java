package com.comast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.Javautilty;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.Fileutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Createorgnization_byindustry_Test2 {
	@Test(groups = "RegressionTest")
	
	

	public void createOrg_industry() throws Throwable {
		
		
		Fileutility flib=new Fileutility();
		ExcelUtility elib=new ExcelUtility();
		
		//read data from property file
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		
		// Read the data from Excel sheet 
		
		//randon number creation
		Javautilty jlib=new Javautilty();
		String org_name = elib.getDtaFromExcel("org", 4, 2)+jlib.getRandomNumber();
		String industry = elib.getDtaFromExcel("org", 4, 3);
		
		
		WebDriver driver=null;
		
	if (BROWSER.equals("chrome")) {
		driver=new ChromeDriver();
		
		}
	else if (BROWSER.equals("firefox")) {
		driver=new FirefoxDriver();
		
		
	}else if (BROWSER.equals("edge")) {
		driver=new EdgeDriver();
		
	}
	else
	{
		driver=new ChromeDriver();
	}
WebDriverUtility wlib=new WebDriverUtility();
wlib.waitForPagetoload(driver);
	driver.manage().window().maximize();
	
	
	//login to the application
	driver.get(URL);
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

	driver.findElement(By.id("submitButton")).click();
	//create orgnization
	
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
	driver.findElement(By.cssSelector("[name=\"accountname\"]")).sendKeys(org_name);
	
	driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	WebElement ind_dropdown = driver.findElement(By.name("industry"));
	wlib.select(ind_dropdown, industry);
	
	
	
	
	//verification of industry is selected or not
	
	String Created_industry = driver.findElement(By.id("dtlview_Industry")).getText();
	
	if(Created_industry.equals(industry))
	{
		System.out.println("choosed industry is selected==== pass");
	}
	else
	{
		System.out.println("choosed industry  is not selected===Fail ");
	}
	
	
	
	
	
		
		

	}

}
