package practice;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class createContactTest {
	
	
	@BeforeSuite
	public void congigBs()
	{
		System.out.println("Before suite");
	}
	@BeforeClass
	public void congigBC()
	{
		System.out.println("Before class");
	}
	@BeforeMethod
	 public void configBM()
	 {
		System.out.println("Before Method");
	 }
	
	@Test
	public void createContact()
	{
		System.out.println("createcontact");
	}
	@Test
	public void createContactwithDate()
	{
		System.out.println("createcontact with date");
	}
	

	@AfterMethod
	public void configAM()
	{
		System.out.println("After Method");
	 }
	
	@AfterClass
	public void congigAC()
	{
		System.out.println("After class");
	}
	@AfterSuite
	public void congigAS()
	{
		System.out.println("After suite");
	}
	
		
	}

