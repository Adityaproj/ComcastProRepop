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
import com.comcast.crm.generic.databaseutility.Webdriverutility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.Fileutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class Createorgnization_byindustrandType_Test3 {
	@Test(groups = "RegressionTest")

	public  void createorgwithType() throws Throwable {
		
		Fileutility flib=new Fileutility();
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		//read data from excel
		Javautilty jlib=new Javautilty();
		ExcelUtility elib=new ExcelUtility();
		String org_name = elib.getDtaFromExcel("org", 7, 2)+jlib.getRandomNumber();
		String industry = elib.getDtaFromExcel("org", 7, 3);
		String type = elib.getDtaFromExcel("org", 7, 3);
		
		
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
	
	
	
	//login to the application
	
	WebDriverUtility wlib=new WebDriverUtility();
	wlib.waitForPagetoload(driver);
	
LoginPage lp=new LoginPage(driver);
lp.Login( URL,USERNAME, PASSWORD);

	//create orgnization
	
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
	driver.findElement(By.cssSelector("[name=\"accountname\"]")).sendKeys(org_name);
	
	driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	
	//Industry dropdown
	WebElement ind_dropdown = driver.findElement(By.name("industry"));
	wlib.select(ind_dropdown, industry);
	
	//type dropdown
	
	WebElement type_dropdown = driver.findElement(By.name("accounttype"));
	wlib.select(type_dropdown, type);
	
	
	
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
	//Verification of type dropdown  is seleted or not
	String created_type = driver.findElement(By.id("dtlview_Type")).getText();
	
	if (created_type.equals(type)) {
		System.out.println("choosed type is selected===pass");
		
	} else {
		System.out.println("choosed type is notselected===Fail");

	}
	
	
	
	
	
	
		
		

	}

}
