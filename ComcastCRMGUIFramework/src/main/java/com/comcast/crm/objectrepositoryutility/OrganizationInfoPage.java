package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	
	 public OrganizationInfoPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath = "//span[@class=\"dvHeaderText\"]")
	 private WebElement orginfo;
	 @FindBy(id = "dtlview_Industry")
	 private WebElement industryinfo;
	 @FindBy(id="dtlview_Organization Name")
	 private WebElement  orgname;
	 @FindBy(id = "dtlview_Type")
	 private WebElement typeinfo;


	public WebElement getTypeinfo() {
		return typeinfo;
	}


	public WebElement getOrgname() {
		return orgname;
	}


	public WebElement getIndustryinfo() {
		return industryinfo;
	}


	public WebElement getOrginfo() {
		return orginfo;
	}
	 

}
