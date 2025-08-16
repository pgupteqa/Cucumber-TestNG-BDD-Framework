package org.automationcart.stepdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationcart.constants.Size;
import org.automationcart.hooks.Hooks;
import org.automationcart.pages.*;
import org.testng.Assert;

public class EndtoEndProductPurchaseSetpDef {

    private MyAccountPage myAccountPage;
    private SearchPage searchPage;
    private ProductDetailPage productDetailPage;
    private ShoppingCartDetailPage shopCartPage;
    private DeliveryAddressPage deliveryAddressPage;
    private ShippingAddressPage shippingAddressPage;
    private PaymentPage paymentPage;
    private String payment_success_msg;
    private final Hooks hooks;
    private HomePage homepage;

    public EndtoEndProductPurchaseSetpDef(Hooks hooks)
    {
        this.hooks = hooks;
        this.homepage = hooks.getHomePage();
        this.myAccountPage = hooks.getMyAccountPage();

    }

    @When("^I search for the product with name as (.+)$")
    public void i_search_for_the_product_with_name(String productname)
    {
        searchPage = myAccountPage.searchForProduct(productname);
    }

    @Then("^I should see the product (.+) in the search results$")
    public void i_should_see_product_in_search_results(String productname)
    {
        Assert.assertTrue(searchPage.isSearchProductPresentInProductList(productname),
                "Product '" + productname + "' is not present in the search results");
    }

    @When("I add the product to the shopping cart and proceed to the checkout")
    public void i_add_product_to_cart_proceed_to_checkout()
    {
        productDetailPage = searchPage.clickOnTheProductTitle(0);

        shopCartPage = productDetailPage.changeSize(Size.M).addProductToTheCart().proceedToCheckout();

        deliveryAddressPage = shopCartPage.goToDeliveryAddressPage();
        shippingAddressPage = deliveryAddressPage.goToShippingAddressPage();
    }

    @Then("I should be able make the payment")
    public void i_should_make_the_payment()
    {
        paymentPage = shippingAddressPage.goToPaymentPage();
        payment_success_msg  = paymentPage.makePaymentByWire();
    }

    @And("I should see the order success message as {string}")
    public void i_should_see_order_success_message(String orderSuccessMsg)
    {
        Assert.assertEquals(payment_success_msg, orderSuccessMsg);
    }
}
