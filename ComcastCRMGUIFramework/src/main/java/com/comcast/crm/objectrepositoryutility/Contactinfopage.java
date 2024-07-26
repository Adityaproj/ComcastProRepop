package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contactinfopage {
	WebDriver driver;
	
	 public Contactinfopage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(className ="dvHeaderText")
	 private WebElement contactHaadinfo;
	 

	 @FindBy(id="dtlview_Last Name")
	 private WebElement lastnameinfo;

	 @FindBy(id="dtlview_Support Start Date")
	 private WebElement startdateinfo;

	 @FindBy(id="dtlview_Support End Date")
	 private WebElement EndDateinfo;
	 @FindBy(id="mouseArea_Organization Name")
	 private WebElement  mouseArea_Organization;
	

	public WebElement getContactHaadinfo() {
		return contactHaadinfo;
	}

	public WebElement getLastnameinfo() {
		return lastnameinfo;
	}

	public WebElement getStartdateinfo() {
		return startdateinfo;
	}

	public WebElement getEndDateinfo() {
		return EndDateinfo;
	}

	public WebElement getMouseArea_Organization() {
		return mouseArea_Organization;
	}
	
	 

}
