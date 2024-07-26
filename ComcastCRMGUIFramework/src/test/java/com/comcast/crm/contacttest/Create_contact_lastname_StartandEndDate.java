package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import com.comcast.crm.objectrepositoryutility.Contactinfopage;
import com.comcast.crm.objectrepositoryutility.Contactpage;
import com.comcast.crm.objectrepositoryutility.Create_new_contact_page;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class Create_contact_lastname_StartandEndDate  {
	@Test(groups = "RegressionTest")

	public void createcontactlastname()throws Throwable {
		
		
		Fileutility flib=new Fileutility();
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		//Excel sheet fetching
		Javautilty jlib=new Javautilty();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		String Last_name = elib.getDtaFromExcel("contact", 1, 2)+jlib.getRandomNumber();
		
		
		
				
		WebDriver driver;
		
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
	
	LoginPage lp=new LoginPage(driver);
	lp.Login(URL, USERNAME, PASSWORD);
	
	// click on contact link
	HomePage hm=new HomePage(driver);
	hm.getContactlink().click();

	//click on create contact img
	Contactpage cp=new Contactpage(driver);
	cp.getCreatecontactBtn().click();
	
	//create contact with last name
	Create_new_contact_page cncp=new Create_new_contact_page(driver);
	cncp.createcontactwithlastname(Last_name);
	//Creation of date
	
	String startdate = jlib.getSystemDate();
	String Enddate = jlib.getrequiredDate(30);
	
	cncp.createcontactwithlastnameandDate(Last_name, startdate, Enddate);
	
	//verification  the start and end date
	Contactinfopage cip=new Contactinfopage(driver);
	WebElement actStartdate = cip.getStartdateinfo();
	WebElement actEndDate = cip.getEndDateinfo();
	
	if (actStartdate.equals(startdate)) {
		System.out.println(startdate+"information is verified==pass");
		
	} else {
		System.out.println(startdate+"information is verified==pass");

	}
	if (actEndDate.equals(Enddate)) {
		System.out.println(Enddate+"information is verified==pass");
		
	} else {
		System.out.println(Enddate+"information is verified==pass");

	}
	
	
	
	
	
	
	
	
	
		
		

	}

}
