package testPkg;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import baseClass.BaseClass;
import pomClasses.HomePage;

public class VerifyWankhede {
	WebDriver driver;
	HomePage hp;
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeClass
	public void beforeClass()
	{
		htmlReporter = new ExtentHtmlReporter("VerifyStadium.html");
		 report = new ExtentReports();
		report.attachReporter(htmlReporter);
		test = report.createTest("VerifyWankhede");
			driver = BaseClass.getDriver("edge");
	}
	
	@BeforeMethod
	public void beforMethod()
	{
		hp = new HomePage(driver);
	}
	@Test(priority = 0)
	public void searchStadium() throws IOException
	{
		hp.enterText();
		hp.getScreenShot(driver, "searchStadium");
	}
	@Test(priority = 1)
	public void verifyName()
	{ 
		Assert.assertTrue(hp.name(), "Stadium Name Verify");
	  
	}
	@Test(priority = 2)
	public void verifyTitle()
	{
		Assert.assertTrue(hp.getTitle(), "Title is verify");
	}
	@Test(priority = 3)
	public void ratingPointAndReviews()
	{
		String r =hp.rating();
		String v = hp.reviews();
		Reporter.log("Rating Point = "+ r + " And Total reviews = "+ v, true);
	}
	@Test(priority = 4)
	public void verifyLink()
	{
		Assert.assertTrue(hp.link(),"Link is verify");
	}
	@Test(priority = 5)
	public void printAddress()
	{  
		System.out.println(hp.address());
		Reporter.log(hp.address(),true);
	}
	@Test(priority = 6)
	public void verifyPhoneNumber() throws IOException
	{
		Assert.assertTrue(hp.phoneNumber());
		hp.getScreenShot(driver, "verifyPhoneNumber");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+"test passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			String path = hp.getScreenShot(driver, result.getName());
			test.log(Status.FAIL, result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else {
			test.log(Status.SKIP, result.getName()+"test skipped");
		}
		
	}
	
	
@AfterClass
public void afterClass() {
	report.flush();
	driver.quit();
}
	

}
