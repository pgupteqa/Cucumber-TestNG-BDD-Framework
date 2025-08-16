package org.automationcart.tests;

import static org.testng.Assert.assertEquals;

import org.automationcart.pojos.InvalidUsers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({ org.automationcart.listeners.TestListener.class })
public class InvalidLoginTest extends TestBase {

	
	@Test(description = "Verify the Login using valid user", groups = {"login","sanity","smoke","regression"}, 
			dataProviderClass = org.automationcart.dataProviders.MultiDataProvider.class,
            dataProvider = "invalidLoginDataProvider")
	public void inValidLoginTest(InvalidUsers invaliduser) {

		assertEquals(homepage.goToLoginPage().doLoginWithInvalidCredentials(invaliduser.getEmailAddress(), invaliduser.getPassword())
				.getErrorMessage(), "Authentication failed.");
		
	}

}
