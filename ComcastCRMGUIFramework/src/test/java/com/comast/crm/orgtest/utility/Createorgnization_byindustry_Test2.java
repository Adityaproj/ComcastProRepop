package com.comast.crm.orgtest.utility;

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
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

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
	//Login to the application
	LoginPage lp = new LoginPage(driver);
	lp.Login(URL, USERNAME, PASSWORD);

	
	//create orgnization
	HomePage hm = new HomePage(driver);
	hm.getOrglink().click();
	OrganizationPage op = new OrganizationPage(driver);
	op.getCreateNeOrgBtn().click();
	
	//create organization with type
	CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	cnop.createorgwithInd(org_name, industry);
	
	
	//verification of industry is selected or not
	OrganizationInfoPage oif=new OrganizationInfoPage(driver);

	String Created_industry=oif.getIndustryinfo().getText();

	
	
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
