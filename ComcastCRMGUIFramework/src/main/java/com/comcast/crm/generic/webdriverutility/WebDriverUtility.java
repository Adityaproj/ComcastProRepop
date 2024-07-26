package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPagetoload(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		
	}
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void switchToTabOnUrl(WebDriver driver,String partialurl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it1 = set.iterator();
		while (it1.hasNext()) {
			String windowid = it1.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partialurl))		
			break;
			}
		}
	
	
	public void switchToTabOnTitle(WebDriver driver,String Title) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it1 = set.iterator();
		while (it1.hasNext()) {
			String windowid = it1.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(Title))		
			break;
			
		}
	}
	public void switchToFrame(WebDriver driver,int index) {
		
		driver.switchTo().frame(index);
		
	}
	
	
   public void switchToFrame(WebDriver driver,String nameid) {
		
		driver.switchTo().frame(nameid);
		
	}
    public void switchToFrame(WebDriver driver,WebElement element) {
	
	driver.switchTo().frame(element);
	
    }
     public void switchToAlertAndAccept(WebDriver driver) {
	
	     driver.switchTo().alert().accept();
	
  }


public void switchToAlertAndDismiss(WebDriver driver) {
	
	driver.switchTo().alert().dismiss();
	
}
public void select(WebElement element,int index) {
	Select s=new Select(element);
	s.selectByIndex(index);
}
public void select(WebElement element,String text) {
	Select s=new Select(element);
	s.selectByVisibleText(text);
}

public void mousemoveToElement(WebDriver driver,WebElement element) {
	Actions act=new Actions(driver);
	act.moveToElement(element).perform();;
	
}

public void doubleclickOnElement(WebDriver driver,WebElement element) {
	Actions act=new Actions(driver);
	act.doubleClick(element).perform();
	
}

public void rightclickOnElement(WebDriver driver,WebElement element) {
	Actions act=new Actions(driver);
	act.contextClick(element).perform();
	
}
public void draganddropOnElement(WebDriver driver,WebElement source,WebElement destination) {
	Actions act=new Actions(driver);
	act.dragAndDrop(source,destination).perform();
	
}

	
	
	
	
	
}
		
	
	
	


