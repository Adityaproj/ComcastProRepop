package com.comcast.crm.generic.assertion;
/**
 * @author adity
 */
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.Contactinfopage;
import com.comcast.crm.objectrepositoryutility.ContactorgnizationPage;
import com.comcast.crm.objectrepositoryutility.Contactpage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.Create_new_contact_page;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

@Listeners( com.comcast.crm.listner.ListnerImpClass.class)

public class createContactTest extends BaseclassTest {
	@Test(groups= "smokeTest")
	public void createContact() throws Throwable {
		// Excel sheet fetching
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
//ListnerImpClass.test.log(Status.INFO, "read data from excel");
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String Last_name = elib.getDtaFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		// click on contact link
		HomePage hm = new HomePage(driver);
		
		hm.getContactlink().click();

		// click on create contact img
		UtilityClassObject.getTest().log(Status.INFO, "create contact");

		Contactpage cp = new Contactpage(driver);
		cp.getCreatecontactBtn().click();

		// create contact with last name
		Create_new_contact_page cncp = new Create_new_contact_page(driver);
		cncp.createcontactwithlastname(Last_name);

		// verification of industry is selected or not
		Contactinfopage cip = new Contactinfopage(driver);

		//Verification of lastname
		String Created_Lastname = cip.getLastnameinfo().getText();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(Created_Lastname, Last_name);
		sa.assertAll();

		// Verification contact header info
		String conHeaderInfo = cip.getContactHaadinfo().getText();
		boolean status = conHeaderInfo.contains(Last_name);
		Assert.assertEquals(status, true);
	}

	@Test
	public void createContactwithDate() throws Throwable {
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
		System.out.println("startdate"+startdate);
		String Enddate = jlib.getrequiredDate(30);
		System.out.println("Enddate"+Enddate);

		cncp.createcontactwithlastnameandDate(Last_name, startdate, Enddate);

		// verification the start and end date
		Contactinfopage cip = new Contactinfopage(driver);
		String actStartdate = cip.getStartdateinfo().getText();
		System.out.println(actStartdate+"start");
		String actEndDate = cip.getEndDateinfo().getText();
		System.out.println(actEndDate+"end");

		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actStartdate, startdate);
		assertobj.assertEquals(actEndDate, Enddate);
		assertobj.assertAll();
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
		boolean orgHeadStatus = orgHeadermsg.contains(org_name);
		Assert.assertEquals(orgHeadStatus, true);
		

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
	SoftAssert sa=new SoftAssert();
	
	
	sa.assertEquals(actorg.trim(),org_name);
	sa.assertAll();
		

	}}
