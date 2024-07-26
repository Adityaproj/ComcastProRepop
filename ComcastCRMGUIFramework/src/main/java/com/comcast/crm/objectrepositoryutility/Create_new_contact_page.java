package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_new_contact_page {
	WebDriver driver;
	
	 public Create_new_contact_page(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(name="lastname")
	 private WebElement lastname;
	 @FindBy(xpath="//input[@title='Save [Alt+S]']")
	 private WebElement SaveBtn;
	 @FindBy(name="support_start_date")
	 private WebElement start_date;
	 @FindBy(name="support_end_date")
	 private WebElement end_date;
	 @FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	 private WebElement conOrgimg;

	public WebElement getLastname() {
		return lastname;
	}
	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	public WebElement getStart_date() {
		return start_date;
	}
	public WebElement getEnd_date() {
		return end_date;
	}
	public WebElement getConOrgimg() {
		return conOrgimg;
	}
	 
	public void createcontactwithlastname(String lasname) {
		lastname.sendKeys(lasname);
		SaveBtn.click();
		}
	public void createcontactwithlastnameandDate(String lasname,String start,String end) {
		lastname.sendKeys(lasname);
		start_date.sendKeys(start);
		end_date.sendKeys(end);
		SaveBtn.click();
		}
	 
	 
	 
	 

}
