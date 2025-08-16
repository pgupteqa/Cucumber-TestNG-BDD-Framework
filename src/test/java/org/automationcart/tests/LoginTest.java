package org.automationcart.tests;

import static org.testng.Assert.*;

import org.automationcart.pojos.InvalidUsers;
import org.automationcart.pojos.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({ org.automationcart.listeners.TestListener.class })
public class LoginTest extends TestBase {

	@Test(description = "Verify the Login using valid user", groups = { "login", "sanity", "smoke",
			"regression" }, dataProviderClass = org.automationcart.dataProviders.MultiDataProvider.class,
            dataProvider = "LoginDataProvider", retryAnalyzer = org.automationcart.listeners.RetryAnalyzer.class)
	public void validLoginTest(User user) {

		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUsername(),
				"Prateek Guptey");

	}

	@Test(description = "Verify the Login using invalid user", groups = { "login", "sanity", "smoke",
			"regression" }, dataProviderClass = org.automationcart.dataProviders.MultiDataProvider.class,
            dataProvider = "invalidLoginDataProvider")
	public void inValidLoginTest(InvalidUsers invaliduser) {

		assertEquals(homepage.goToLoginPage()
				.doLoginWithInvalidCredentials(invaliduser.getEmailAddress(), invaliduser.getPassword())
				.getErrorMessage(), "Authentication failed.");
	}
	/*
	 * @Test(description="Verify the Login using valid user using CSV", groups=
	 * {"login","sanity","smoke"},
	 * dataProviderClass=com.ui.dataproviders.MultiDataProvider.class,
	 * dataProvider="LoginTestCSVDataProvider") public void loginCSVTest(User user)
	 * {
	 * 
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUsername(), "Prateek Guptey"); }
	 * 
	 * @Test(description="Verify the Login using valid user using CSV", groups=
	 * {"login","sanity","smoke"},
	 * dataProviderClass=com.ui.dataproviders.MultiDataProvider.class,
	 * dataProvider="LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.RetryAnalyzer.class) public void loginExcelTest(User user) {
	 * assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUsername(), "Prateek Guptey"); }
	 */

}
