package practice;

import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	 public  void getProductInfoTest(String Brandname,String productname) {
		WebDriver driver=new  ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
		String x="(//span[text()='"+productname+"'])[1]/../../../../div[3]/div/div/div/div/div/a/span/span/span[2]";
		String product_price = driver.findElement(By.xpath(x)).getText();
		System.out.println(productname+":"+product_price);
		
		
	
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		
		ExcelUtility elib=new ExcelUtility();
		int rowcount = elib.getrowcount("product_amz");
	
		Object [][]objrr=new Object[rowcount][2];
		for (int i = 0; i < rowcount; i++) {
			objrr[i][0]=elib.getDtaFromExcel("product_amz", i+1, 0);
			objrr[i][1]=elib.getDtaFromExcel("product_amz", i+1, 1);
			
		}
		
		return objrr;
}
}
