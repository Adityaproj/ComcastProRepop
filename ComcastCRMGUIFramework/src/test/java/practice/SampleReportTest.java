package practice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// Spark report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancrReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "Chrome-100");
	}

	@AfterSuite
	public void configAS() {
		report.flush();

	}

	@Test
	public void creatContactTest() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		TakesScreenshot edriver=  (TakesScreenshot) driver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
	

		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("Hdfc".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			//test.log(Status.FAIL, "contact is not created");
			test.addScreenCaptureFromBase64String(filepath, "ErrorShots");
		}
		//test.log(Status.INFO, "login to app");
		driver.quit();

	}

//	@Test
//	public void creatContactWithOrg() {
//
//		ExtentTest test = report.createTest("creatContactWithOrg");
//
//		test.log(Status.INFO, "login to app");
//		test.log(Status.INFO, "navigate to contact page");
//		test.log(Status.INFO, "create contact");
//		if ("HDFC".equals("HDFC")) {
//			test.log(Status.PASS, "PASS");
//		} else {
//			test.log(Status.FAIL, "contact is not created");
//		}
//		test.log(Status.INFO, "login to app");
//
//	}
//
//	@Test
//	public void creatContactWithPhoneNumber() {
//
//		ExtentTest test = report.createTest("creatContactWithPhoneNumber");
//
//		test.log(Status.INFO, "login to app");
//		test.log(Status.INFO, "navigate to contact page");
//		test.log(Status.INFO, "create contact");
//		if ("HDFC".equals("HDFC")) {
//			test.log(Status.PASS, "PASS");
//		} else {
//			test.log(Status.FAIL, "contact is not created");
//		}
//		test.log(Status.INFO, "login to app");
//
//	}
//
}
