package com.comcast.crm.generic.basicutility;

import org.testng.annotations.Test;

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
			if(Created_industry.equals(industry))
			{
				System.out.println("choosed industry is selected==== pass");
			}
			else
			{
				System.out.println("choosed industry  is not selected===Fail ");
			}
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

			
			
			if(Created_industry.equals(industry))
			{
				System.out.println("choosed industry is selected==== pass");
			}
			else
			{
				System.out.println("choosed industry  is not selected===Fail ");
			}
			//Verification of type dropdown  is seleted or not
			String created_type = oif.getTypeinfo().getText();
			
			if (created_type.equals(type)) {
				System.out.println("choosed type is selected===pass");
				
			} else {
				System.out.println("choosed type is notselected===Fail");

			}
			
			
		}
		

}
