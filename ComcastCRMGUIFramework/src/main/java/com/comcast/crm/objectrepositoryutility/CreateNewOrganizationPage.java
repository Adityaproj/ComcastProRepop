package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage {
	WebDriver driver;
	
	 public CreateNewOrganizationPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(name="accountname")
	 private WebElement orgnameEdt;
	 @FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
	 private WebElement saveBtn;
	 
	  @FindBy(name="industry")
	 private WebElement industryDD;
	  
	  @FindBy(name="accounttype")
	private WebElement  accounttypeDD;

	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	 
	public WebElement getIndustryDD() {
		return industryDD;
	}
	public WebElement getAccounttype() {
		return accounttypeDD;
	}
	public void createorg(String orgName) {
		orgnameEdt.sendKeys(orgName);
		saveBtn.click();
		
		
	}
	
	public void createorgwithInd(String orgName,String industry) {
		orgnameEdt.sendKeys(orgName);
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.select(industryDD, industry);
		saveBtn.click();
	}
	public void createorgwithIndandType(String orgName,String industry,String type) {
		orgnameEdt.sendKeys(orgName);
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.select(industryDD, industry);
		wlib.select(accounttypeDD, type);
		saveBtn.click();
	}
	

}
