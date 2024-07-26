package com.comcast.crm.generic.basicutility.crossbrowsertest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.Contactinfopage;
import com.comcast.crm.objectrepositoryutility.ContactorgnizationPage;
import com.comcast.crm.objectrepositoryutility.Contactpage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.Create_new_contact_page;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class createContactTest extends BaseclassTest {
	@Test
	public void createContact() throws Throwable {
		// Excel sheet fetching

		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		// click on contact link
		HomePage hm = new HomePage(driver);
		hm.getContactlink().click();

		// click on create contact img
		Contactpage cp = new Contactpage(driver);
		cp.getCreatecontactBtn().click();

		// create contact with last name
		Create_new_contact_page cncp = new Create_new_contact_page(driver);
		cncp.createcontactwithlastname(Last_name);

		// verification of industry is selected or not
		Contactinfopage cip = new Contactinfopage(driver);

		//// Verification of lastname
		String Created_Lastname = cip.getLastnameinfo().getText();

		if (Created_Lastname.equals(Last_name)) {
			System.out.println("lastname is available==== pass");
		} else {
			System.out.println("lastname is not available===Fail ");
		}
		// Verification contact header info
		String created_contact = cip.getContactHaadinfo().getText();

		if (created_contact.contains(Last_name)) {
			System.out.println("contact is created it contains last name ===pass");

		} else {
			System.out.println("created contact not contain last name===Fail");

		}

	}

	@Test
	public void createContactwithDate() throws Throwable {
		System.out.println("createcontact with date and verify");
		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		// click on contact link
		HomePage hm = new HomePage(driver);
		hm.getContactlink().click();

		// click on create contact img
		Contactpage cp = new Contactpage(driver);
		cp.getCreatecontactBtn().click();

		// create contact with last name
		Create_new_contact_page cncp = new Create_new_contact_page(driver);
		// Creation of date

		String startdate = jlib.getSystemDate();
		String Enddate = jlib.getrequiredDate(30);

		cncp.createcontactwithlastnameandDate(Last_name, startdate, Enddate);

		// verification the start and end date
		Contactinfopage cip = new Contactinfopage(driver);
		WebElement actStartdate = cip.getStartdateinfo();
		WebElement actEndDate = cip.getEndDateinfo();

		if (actStartdate.equals(startdate)) {
			System.out.println(startdate + "information is verified==pass");

		} else {
			System.out.println(startdate + "information is verified==pass");

		}
		if (actEndDate.equals(Enddate)) {
			System.out.println(Enddate + "information is verified==pass");

		} else {
			System.out.println(Enddate + "information is verified==pass");

		}

	}

	@Test
	public void createconwithorg() throws Throwable {
		String org_name = elib.getDtaFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastname = elib.getDtaFromExcel("contact", 7, 3);
		// create orgnization
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		// click on create org buttton
		OrganizationPage org = new OrganizationPage(driver);
		org.getCreateNeOrgBtn().click();

		// enter the details and create org
		CreateNewOrganizationPage crp = new CreateNewOrganizationPage(driver);
		crp.createorg(org_name);

		// verify the header message
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

		// click on create contact img
		Contactpage cp = new Contactpage(driver);
		cp.getCreatecontactBtn().click();

		// create contact with last name
		Create_new_contact_page cncp = new Create_new_contact_page(driver);
		cncp.getLastname().sendKeys(contactLastname);
		cncp.getConOrgimg().click();

		// switch to child window

		wlib.switchToTabOnTitle(driver, "module=Accounts");

		ContactorgnizationPage cop = new ContactorgnizationPage(driver);

		cop.getSearchtext().sendKeys(org_name);
	cop.searchDropDpwn();
		cop.getSubmit().click();
		driver.findElement(By.xpath("//a[text()='" + org_name + "']")).click();
		// switch to parent window
		Set<String> set1 = driver.getWindowHandles();
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
