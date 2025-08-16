package org.automationcart.tests;

import org.automationcart.pages.MyAccountPage;
import org.automationcart.pojos.AddressPOJO;
import org.automationcart.pojos.User;
import org.automationcart.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.Test;




public class AddNewAddressTest extends TestBase {
	
	private MyAccountPage myAccountPage;
	private AddressPOJO address;
	
	@Test(description="Verify if the logged in user is able to add the new address",
            groups= {"addNewAddress","smoke","regression"},
            dataProviderClass=org.automationcart.dataProviders.MultiDataProvider.class,
			dataProvider="ValidLoginDataProvider")
	public void addNewAddressTest(User user)
	{
		myAccountPage = loginAsValidUser(user);
		address= FakeAddressUtility.getFakeAddressData();
		Assert.assertTrue(myAccountPage.validateMyFirstAddressOption(),"The Add New Address Option is not visible.");
		String newAddress=myAccountPage.goToAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
	}
	
	
	

}
