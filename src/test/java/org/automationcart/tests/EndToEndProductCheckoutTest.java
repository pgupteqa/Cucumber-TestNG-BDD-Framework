package org.automationcart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.automationcart.constants.Env;
import org.automationcart.constants.Size;
import org.automationcart.pages.DeliveryAddressPage;
import org.automationcart.pages.MyAccountPage;
import org.automationcart.pages.PaymentPage;
import org.automationcart.pages.ProductDetailPage;
import org.automationcart.pages.SearchPage;
import org.automationcart.pages.ShippingAddressPage;
import org.automationcart.pages.ShoppingCartDetailPage;
import org.automationcart.pojos.User;
import org.automationcart.utility.PropertiesUtil;

@Listeners({ org.automationcart.listeners.TestListener.class })
public class EndToEndProductCheckoutTest extends TestBase {

	private static final String PRODUCT_SEARCH_TERM = PropertiesUtil.readProperty(Env.DEV, "PRODUCTSEARCHTERM");
	private MyAccountPage myAccountPage;
	private SearchPage searchPage;
	private ProductDetailPage productDetailPage;
	private ShoppingCartDetailPage shopCartPage;
	private DeliveryAddressPage deliveryAddressPage;
	private ShippingAddressPage shippingAddressPage;
	private PaymentPage paymentPage;
	private static final String PAYMENT_SUCCESS_MESSAGE_TEXT = "Your order on My Shop is complete.";

	@Test(description = "User selects the product and proceed to checkout", groups = { "smoke", "sanity",
			"regression" }, dataProviderClass = org.automationcart.dataProviders.MultiDataProvider.class,
            dataProvider = "ValidLoginDataProvider", retryAnalyzer = org.automationcart.listeners.RetryAnalyzer.class)
	public void endToEndProductcheckoutTest(User user) {
		myAccountPage = loginAsValidUser(user);

		searchPage = myAccountPage.searchForProduct(PRODUCT_SEARCH_TERM);
		Assert.assertTrue(searchPage.isSearchProductPresentInProductList(PRODUCT_SEARCH_TERM),
				"Product '" + PRODUCT_SEARCH_TERM + "' is not present in the search results");

		productDetailPage = searchPage.clickOnTheProductTitle(0);

		shopCartPage = productDetailPage.changeSize(Size.M).addProductToTheCart().proceedToCheckout();

		deliveryAddressPage = shopCartPage.goToDeliveryAddressPage();
		shippingAddressPage = deliveryAddressPage.goToShippingAddressPage();
		paymentPage = shippingAddressPage.goToPaymentPage();

		String payment_success_msg = paymentPage.makePaymentByWire();
		Assert.assertEquals(payment_success_msg, PAYMENT_SUCCESS_MESSAGE_TEXT);

	}

}
