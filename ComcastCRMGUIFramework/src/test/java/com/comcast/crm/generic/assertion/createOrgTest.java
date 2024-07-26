package com.comcast.crm.generic.assertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class createOrgTest extends BaseclassTest{
	
		@Test
		public void createorg() throws Throwable
		{
			//read data form Excel file
			String org_name = elib.getDtaFromExcel("org", 1, 2)+jlib.getRandomNumber();
			// click on orgnization image
			HomePage hm = new HomePage(driver);
			hm.getOrglink().click();
			OrganizationPage op = new OrganizationPage(driver);
			op.getCreateNeOrgBtn().click();

			//create org with name
			CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
			cnp.createorg(org_name);
			
			// validation
			OrganizationInfoPage oif = new OrganizationInfoPage(driver);

			String org_name_heading = oif.getOrginfo().getText();
			boolean orHeadinfo = org_name_heading.contains(org_name);
			
			Assert.assertEquals(orHeadinfo,true);
		
			String name = oif.getOrgname().getText();
			SoftAssert assertobj=new SoftAssert();
			assertobj.assertEquals(name, org_name);
			assertobj.assertAll();
			}
		@Test
		public void createOrgwithIndustry() throws Throwable
		{
			System.out.println("createOrg with industry and verify");
			String org_name = elib.getDtaFromExcel("org", 4, 2)+jlib.getRandomNumber();
			String industry = elib.getDtaFromExcel("org", 4, 3);
			//click on orgnization image
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
			Assert.assertEquals(Created_industry, industry);
			
		}
		@Test
		
		public void createorgwithIndandType() throws Throwable
		{
			String org_name = elib.getDtaFromExcel("org", 7, 2)+jlib.getRandomNumber();
			String industry = elib.getDtaFromExcel("org", 7, 3);
			String type = elib.getDtaFromExcel("org", 7, 4);
			
			//create orgnization
			HomePage hm = new HomePage(driver);
			hm.getOrglink().click();
			OrganizationPage op = new OrganizationPage(driver);
			op.getCreateNeOrgBtn().click();
			
			//create organization with type
			CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
			cnop.createorgwithIndandType(org_name, industry, type);
			
			
			//verification of industry is selected or not
			OrganizationInfoPage oif=new OrganizationInfoPage(driver);
			
			String Created_industry=oif.getIndustryinfo().getText();
			SoftAssert assertobj=new SoftAssert();
			assertobj.assertEquals(Created_industry, industry);

			//Verification of type dropdown  is seleted or not
			String created_type = oif.getTypeinfo().getText();
			assertobj.assertEquals(created_type, type);
			assertobj.assertAll();
			
			
		}
		

}
