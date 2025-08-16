package org.automationcart.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.automationcart.hooks.Hooks;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.ExtentReporterUtility;
import org.automationcart.utility.LoggerUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestListener implements ITestListener {

	Logger logger= LoggerUtility.getLogger(this.getClass());
	
	ExtentSparkReporter extentSparkReporter; // used to create the html file
	ExtentReports extentReports; // data dump into the html file
	ExtentTest extentTest; // store information about test
	
	@Override
	public void onTestStart(ITestResult result) {
		/*gives the test methodname, description and test group names like smoke, sanity, regression */
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		//extentTest = extentReports.createTest(result.getMethod().getMethodName());
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logger.info(result.getMethod().getMethodName() + " " +"PASSED");
		ExtentReporterUtility.getTest().log(Status.PASS,result.getMethod().getMethodName() + " " +"PASSED");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		logger.error(result.getMethod().getMethodName() + " " +"FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getMethod().getMethodName() + " " +"FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage() + " " +"FAILED");
		
		Object testclass = result.getInstance();
		BrowserUtility browserutility = ((Hooks)testclass).getInstance();
		logger.info("Capturing screen shot on failed tests");
		
		String screenshotPath=browserutility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the Screenshot in the report");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		logger.warn(result.getMethod().getMethodName() + " " +"SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP,result.getMethod().getMethodName() + " " +"SKIPPED");
	}

	@Override
	public void onStart(ITestContext context) {
		
		//Suite started
		logger.info("Test Suite Started");
		ExtentReporterUtility.setupSparkReporter("report.html");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		//Suite Completed
		logger.info("Test Suite Completed");
		ExtentReporterUtility.flushReport(); //Create a report
	}

}
