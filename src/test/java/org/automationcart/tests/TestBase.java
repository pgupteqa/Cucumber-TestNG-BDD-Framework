package org.automationcart.tests;

import org.apache.logging.log4j.Logger;
import org.automationcart.constants.Browser;
import org.automationcart.constants.Env;
import org.automationcart.pages.HomePage;
import org.automationcart.pages.LoginPage;
import org.automationcart.pages.MyAccountPage;
import org.automationcart.pojos.InvalidUsers;
import org.automationcart.pojos.User;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LambdaTestUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class TestBase {

	protected HomePage homepage;
	protected MyAccountPage myAccountPage;
	protected LoginPage loginPage;
	protected User user; // current user object
	protected InvalidUsers invaliduser;
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isUserLoggedIn = false;
	private boolean isLambdaTest;

	@Parameters({ "browsername", "isLambdaTest", "isHeadLess", "enviornmentname" })
	@BeforeMethod(description = "Load the homepage of the website", alwaysRun = true)
	public void setup(@Optional("chrome") String browsername, @Optional("false") boolean isLambdaTest,
			@Optional("false") boolean isHeadLess, @Optional("QA") String enviornmentname, ITestResult result) {

		this.isLambdaTest = isLambdaTest; // instance variable
		WebDriver lambdadriver;

		if (isLambdaTest) {

			lambdadriver = LambdaTestUtility.initializeLambdaTest(browsername, result.getMethod().getMethodName());
			homepage = new HomePage(lambdadriver);

		} else {
			// Runs on the local machine
			logger.info("Loads the HomePage of the website");
			homepage = new HomePage(Browser.valueOf(browsername.toUpperCase()),
					Env.valueOf(enviornmentname.toUpperCase()), isHeadLess);
		}

		isUserLoggedIn = false;
	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	// Common login method that can be reused in multiple tests where login requires
	// eg: Product purchase
	// Handles reusable login logic with assertion
	public MyAccountPage loginAsValidUser(User user) {

		this.user = user; // instance variable for user

		if (!isUserLoggedIn) {
			logger.info("Login as Valid User " + user.getEmailAddress());
			// test.info("Login as a Valid user with email as " + user.getEmailAddress());
			myAccountPage = homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword());
			isUserLoggedIn = true;
		}

		return myAccountPage;
	}

	// Common login method that can be reused in multiple tests where login requires
	// eg: Product purchase
	// Handles reusable login logic with assertion
	public LoginPage loginAsInvalidValidUser(InvalidUsers invaliduser) {

		this.invaliduser = invaliduser; // instance variable for user

		logger.info("Login as Invalid User " + invaliduser.getEmailAddress());
		homepage.goToLoginPage().doLoginWith(invaliduser.getEmailAddress(), invaliduser.getPassword());
		return loginPage;
	}

	@AfterMethod(description = "Tear down the browser", alwaysRun = true)
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // quit the lambdatest browser session
		} else {
			homepage.quitBrowserSession(); // quit the local browser session
		}
	}
}
