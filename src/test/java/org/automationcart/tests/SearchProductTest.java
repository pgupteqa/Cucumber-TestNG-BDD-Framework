package org.automationcart.tests;

import org.apache.logging.log4j.Logger;
import org.automationcart.constants.Env;
import org.automationcart.pages.MyAccountPage;
import org.automationcart.pojos.User;
import org.automationcart.utility.LoggerUtility;
import org.automationcart.utility.PropertiesUtil;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners({org.automationcart.listeners.TestListener.class})
public class SearchProductTest extends TestBase{
	
	Logger logger= LoggerUtility.getLogger(this.getClass());
	private MyAccountPage myAccountPage;
	private static final String PRODUCT_SEARCH_TERM = PropertiesUtil.readProperty(Env.DEV,"PRODUCTSEARCHTERM");
	
	
	@Test(description="Verify if the logged in user is able to search for the correct product", groups= {"sanity","regression","smoke"},
            dataProviderClass=org.automationcart.dataProviders.MultiDataProvider.class,
			dataProvider="ValidLoginDataProvider")
	public void verifyProductSearch(User user)
	{	
		myAccountPage=loginAsValidUser(user);
		boolean status =myAccountPage.searchForProduct(PRODUCT_SEARCH_TERM).isSearchProductPresentInProductList(PRODUCT_SEARCH_TERM);
		Assert.assertEquals(status, true);
		
	}

}
