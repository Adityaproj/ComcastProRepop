package com.comcast.crm.listner;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListnerImpClass implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");
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

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

		System.out.println("Report backup");
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>" + result.getMethod().getMethodName() + "<=== start===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"==> STARTED <=====");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>" + result.getMethod().getMethodName() + "<=== End===");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==> COMPLETED <=====");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// ITestListener.super.onTestFailure(result);
		String testName = result.getMethod().getMethodName();
		TakesScreenshot edriver = (TakesScreenshot) BaseclassTest.sdriver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==>FAILED <=====");

//		System.out.println(time);
//		
//		
//		File dest=new File("./screenshot/"+testName+"+"+time+".png");
//		
//		try {
//			org.openqa.selenium.io.FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stu
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
