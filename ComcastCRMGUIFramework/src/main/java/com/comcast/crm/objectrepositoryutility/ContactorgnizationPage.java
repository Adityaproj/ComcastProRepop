package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ContactorgnizationPage {
	WebDriver driver;
	
	 public ContactorgnizationPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 @FindBy(name="search_text")
		private WebElement searchtext;
		
		@FindBy(name="search_field")
		private WebElement searchfield;
		
		@FindBy(name="search")
		private WebElement submit;

		public WebElement getSearchtext() {
			return searchtext;
		}



		public WebElement getSearchfield() {
			return searchfield;
		}
		
		
		public WebElement getSubmit() {
			return submit;
		}
		
		public void searchDropDpwn()
		{
			WebDriverUtility wlib=new WebDriverUtility();
			wlib.select(searchfield, "Organization Name");
		}
		

}
