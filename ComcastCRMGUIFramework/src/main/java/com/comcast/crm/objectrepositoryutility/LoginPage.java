package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.databaseutility.Webdriverutility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

//step1:class creation
public class LoginPage extends WebDriverUtility {
	WebDriver driver;
	
	//object inialization
	public LoginPage(WebDriver driver)

	{
		this.driver=driver;
		  PageFactory.initElements(driver, this);
	}
	
	//step2&4:object identification and object encapsulation
	
	
	@FindBy(name="user_name")
	 private WebElement username;
	
	@FindBy(name="user_password")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	

	

	//
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
//step5:object utilization
	public void Login( String url,String username, String password) {
		waitForPagetoload(driver);
		driver.get(url);
		driver.manage().window().maximize();
		this.username.sendKeys(username);
	getPassword().sendKeys(password);
	getLoginButton().click();
		
		
	}
	
	
	
	

}
