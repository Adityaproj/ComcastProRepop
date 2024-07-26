package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//object inialization
	public HomePage(WebDriver driver)
	
	{
		this.driver=driver;
		  PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Product")
	private WebElement productlink;
	
@FindBy(linkText = "Organizations")
private WebElement orglink;

@FindBy(linkText = "Contacts")
private WebElement contactlink;

@FindBy(linkText = "Campaigns")
private WebElement campaignlink;

@FindBy(linkText = "More")
private WebElement morelink;

@FindBy(xpath = "//img[@src=\"themes/softed/images/user.PNG\"]")
private WebElement Adminimg;

@FindBy(linkText = "Sign Out")

private WebElement signout;

public WebElement getCampaignlink() {
	return campaignlink;
}

public WebElement getMorelink() {
	return morelink;
}

public WebElement getOrglink() {
	return orglink;
}

public WebElement getContactlink() {
	return contactlink;
}

public WebElement getAdminimg() {
	return Adminimg;
}

public WebElement getSignout() {
	return signout;
}


public void navigateToCampagin() {
	Actions act=new Actions(driver);
	
	act.moveToElement(morelink);
	campaignlink.click();
	
}
public void Signout() {
	Actions act=new Actions(driver);
	act.moveToElement(Adminimg).perform();
	signout.click();
	
}

}
