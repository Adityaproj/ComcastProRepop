package com.comcast.crm.contacttest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basicutility.BaseclassTest;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class contact extends BaseclassTest {
	@Test

	public void contactTest() throws Throwable {
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.waitForPagetoload(driver);

		// Excel sheet fetching

		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		

		// create orgnization

		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(Last_name);

		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		// verification of industry is selected or not

		String Created_Lastname = driver.findElement(By.id("dtlview_Last Name")).getText();

		if (Created_Lastname.equals(Last_name)) {
			System.out.println("lastname is available==== pass");
		} else {
			System.out.println("lastname is not available===Fail ");
		}
		// Verification of type dropdown is seleted or not
		String created_contact = driver.findElement(By.className("dvHeaderText")).getText();

		if (created_contact.contains(Last_name)) {
			System.out.println("contact is created it contains last name ===pass");

		} else {
			System.out.println("created contact not contain last name===Fail");

		}
	}
	@Test
	public void contact_supportdate() throws Throwable
	{
		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(Last_name);
		//Creation of date
		
		String startdate = jlib.getSystemDate();
		String Enddate = jlib.getrequiredDate(30);
		
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		driver.findElement(By.name("support_end_date")).sendKeys(Enddate);
		
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		
		
		
		//verification  the start and end date
		WebElement actStartdate = driver.findElement(By.id("dtlview_Support Start Date"));
		WebElement actEndDate = driver.findElement(By.id("dtlview_Support End Date"));
		
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
	@Test
	public void contact_org() throws Throwable
	{
		String org_name = elib.getDtaFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		 String contactLastname = elib.getDtaFromExcel("contact", 7,3);
		

	
	//create orgnization
	HomePage hp=new HomePage(driver);
	hp.getOrglink().click();
//click on create org buttton	
OrganizationPage org=new OrganizationPage(driver);
org.getCreateNeOrgBtn().click();

//enter the details and create org 
CreateNewOrganizationPage crp=new CreateNewOrganizationPage(driver);
crp.createorg(org_name);
	
//verify the header message
OrganizationInfoPage orginfo=new OrganizationInfoPage(driver);
String orgHeadermsg = orginfo.getOrginfo().getText();
if(orgHeadermsg.contains(org_name))
{
	System.out.println(org_name+"org name is verified==pass");
}
else
{
	System.out.println(org_name+"org name is not verified==fail");
	
}
	


	//navigate to contact
	Thread.sleep(2000);
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
	driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(contactLastname);
	driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();
	
	//switch to child window
	
wlib.switchToTabOnTitle(driver, "module=Accounts");
	
	
	
	driver.findElement(By.id("search_txt")).sendKeys(org_name);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[text()='"+org_name+"']")).click();
	//switch to parent window
		Set<String> set1 = driver.getWindowHandles();
//		for (String string : set1) {
//			driver.switchTo().window(string);
//			String acturl = driver.getCurrentUrl();
//			if(acturl.contains("Contacts&action"))
//			
//			break;
//			
//		}
		wlib.switchToTabOnTitle(driver,"Contacts&action" );
		
	driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	
	//verify the orgname in contact 
	String actorg = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actorg);
	System.out.println(org_name);
	if(actorg.trim().equalsIgnoreCase(org_name))
	{
		System.out.println(org_name+"information is created==pass");
		
	}
	else
	{
		System.out.println(org_name+"information is  not created==Fail");
	}
	
	

	}


}
