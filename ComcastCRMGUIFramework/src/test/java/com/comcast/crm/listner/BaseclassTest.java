package com.comcast.crm.listner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.databaseutility.Javautilty;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.Fileutility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseclassTest {
	
	
	public DatabaseUtility db=new  DatabaseUtility();
	public Fileutility flib=new Fileutility();
	
	public Javautilty jlib=new Javautilty();
	public ExcelUtility elib=new ExcelUtility();
    public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
    
	 
	@BeforeSuite
	public void congigBs() throws Throwable
	{
		System.out.println("connect to DB");
		
		db.getDbconnection();
	}


	@BeforeClass
	public void congigBC() throws Throwable
	{
		System.out.println("launch the browser");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
	
		
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
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

@BeforeMethod
	 public void configBM() throws Throwable
	 {
		System.out.println("Login to the application");
		LoginPage lp=new LoginPage(driver);
		String URL=flib.getDataFromPropertiesFile("url");
		String USERNAME=flib.getDataFromPropertiesFile("username");
		String PASSWORD=flib.getDataFromPropertiesFile("password");
		lp.Login(URL, USERNAME, PASSWORD);
		 }
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("Logout the apllication ");
		HomePage hm=new HomePage(driver);
		hm.Signout();
	 }
	
	@AfterClass
	public void congigAC() throws Throwable
	{
		System.out.println("Close the browser");
		driver.quit();
	
	}
	@AfterSuite
	public void congigAS() throws Throwable
	{
		System.out.println("close the DB");
		db.closeDbConnection();
	}
	
		
	}


