package com.comcast.crm.contacttest;

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

import com.comcast.crm.generic.basicutility.BaseclassTest;
import com.comcast.crm.generic.databaseutility.Javautilty;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.Fileutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.Contactinfopage;
import com.comcast.crm.objectrepositoryutility.Contactpage;
import com.comcast.crm.objectrepositoryutility.Create_new_contact_page;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class Create_contact_lastname_Test  {
	@Test(groups = "somkeTest")

	public void contactTest() throws Throwable {
		Fileutility flib=new Fileutility();
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		
		//Excel sheet fetching
		Javautilty jlib=new Javautilty();
		ExcelUtility elib=new ExcelUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		WebDriver driver=null;

		// Excel sheet fetching

		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();

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
		

		// verification of industry is selected or not
		Contactinfopage cip=new Contactinfopage(driver);
		

		String Created_Lastname = cip.getLastnameinfo().getText();

		if (Created_Lastname.equals(Last_name)) {
			System.out.println("lastname is available==== pass");
		} else {
			System.out.println("lastname is not available===Fail ");
		}
		// Verification of type dropdown is seleted or not
		String created_contact = cip.getContactHaadinfo().getText();

		if (created_contact.contains(Last_name)) {
			System.out.println("contact is created it contains last name ===pass");

		} else {
			System.out.println("created contact not contain last name===Fail");

		}

	}

}
