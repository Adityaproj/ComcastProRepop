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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class Createorgnization_Test {
	@Test(groups = "smokeTest")

	public void createorg() throws Throwable {

		// create object file utilty
		Fileutility fu = new Fileutility();
		ExcelUtility eu = new ExcelUtility();

		// read data from property file
		String BROWSER = fu.getDataFromPropertiesFile("browser");
		String URL = fu.getDataFromPropertiesFile("url");
		String USERNAME = fu.getDataFromPropertiesFile("username");
		String PASSWORD = fu.getDataFromPropertiesFile("password");

		// randon number creation
		Javautilty jlib = new Javautilty();

		// read data form Excel file
		String org_name = eu.getDtaFromExcel("org", 1, 2) + jlib.getRandomNumber();

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
		// driver.get(URL);

		// create orgnization
		LoginPage lp = new LoginPage(driver);
		lp.Login(URL, USERNAME, PASSWORD);

		HomePage hm = new HomePage(driver);
		hm.getOrglink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNeOrgBtn().click();

		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createorg(org_name);

		// validation
		OrganizationInfoPage oif = new OrganizationInfoPage(driver);

		String org_name_heading = oif.getOrginfo().getText();
		if (org_name_heading.contains(org_name)) {
			System.out.println("orgnization name is created");
		} else {
			System.out.println("orgnization name is not created");
		}

		String name = oif.getOrgname().getText();
		if (name.equals(org_name)) {
			System.out.println("orgnization  created is valid");

		} else {
			System.out.println("organization created is not valid");
		}

	}

}
