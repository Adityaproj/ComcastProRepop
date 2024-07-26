package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.databaseutility.Webdriverutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationPage {
	WebDriver driver;
	
	 public OrganizationPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	
	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement createNeOrgBtn;
	
	
	

	public WebElement getCreateNeOrgBtn() {
		return createNeOrgBtn;
	}






	

}

