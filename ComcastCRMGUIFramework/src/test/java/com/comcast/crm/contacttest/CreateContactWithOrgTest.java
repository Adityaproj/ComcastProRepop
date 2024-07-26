package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.Javautilty;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.Fileutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.Contactinfopage;
import com.comcast.crm.objectrepositoryutility.ContactorgnizationPage;
import com.comcast.crm.objectrepositoryutility.Contactpage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.Create_new_contact_page;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactWithOrgTest {
	@Test(groups = "RegressionTest")

	public void CreateContactWithOrgTest() throws Throwable {
		// create orgnization
		Fileutility flib = new Fileutility();
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		// Excel sheet fetching
		Javautilty jlib = new Javautilty();
		ExcelUtility elib = new ExcelUtility();
		String org_name = elib.getDtaFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastname = elib.getDtaFromExcel("contact", 7, 3);

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		} else {
			driver = new ChromeDriver();
		}
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPagetoload(driver);

		// login to the application
		LoginPage lp = new LoginPage(driver);
		lp.Login(URL, USERNAME, PASSWORD);

		// create orgnization
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
//click on create org buttton	
		OrganizationPage org = new OrganizationPage(driver);
		org.getCreateNeOrgBtn().click();

//enter the details and create org 
		CreateNewOrganizationPage crp = new CreateNewOrganizationPage(driver);
		crp.createorg(org_name);

//verify the header message
		OrganizationInfoPage orginfo = new OrganizationInfoPage(driver);
		String orgHeadermsg = orginfo.getOrginfo().getText();
		if (orgHeadermsg.contains(org_name)) {
			System.out.println(org_name + "org name is verified==pass");
		} else {
			System.out.println(org_name + "org name is not verified==fail");

		}

		// navigate to contact
		wlib.waitForPagetoload(driver);

// click on contact link
		HomePage hm = new HomePage(driver);
		hm.getContactlink().click();

//click on create contact img
		Contactpage cp = new Contactpage(driver);
		cp.getCreatecontactBtn().click();

//create contact with last name
		Create_new_contact_page cncp = new Create_new_contact_page(driver);
		cncp.getLastname().sendKeys(contactLastname);
		cncp.getConOrgimg().click();

		// switch to child window

		wlib.switchToTabOnTitle(driver, "module=Accounts");

		ContactorgnizationPage cop = new ContactorgnizationPage(driver);

		cop.getSearchtext().sendKeys(org_name);
		cop.searchDropDpwn();
		cop.getSearchfield().click();
		driver.findElement(By.xpath("//a[text()='" + org_name + "']")).click();
		// switch to parent window
		Set<String> set1 = driver.getWindowHandles();
//		for (String string : set1) {
//			driver.switchTo().window(string);
//			String acturl = driver.getCurrentUrl();
//			if(acturl.contains("Contacts&action"))
//			
//			break;
//			
//		}
		wlib.switchToTabOnTitle(driver, "Contacts&action");

		cncp.getSaveBtn().click();

		// verify the orgname in contact
		Contactinfopage cip = new Contactinfopage(driver);
		String actorg = cip.getMouseArea_Organization().getText();
		System.out.println(actorg);
		System.out.println(org_name);
		if (actorg.trim().equalsIgnoreCase(org_name)) {
			System.out.println(org_name + "information is created==pass");

		} else {
			System.out.println(org_name + "information is  not created==Fail");
		}

	}

}
