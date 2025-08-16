package org.automationcart.pages;


import org.automationcart.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartDetailPage extends BrowserUtility {

	private static final By SHOPPING_CART_HEADING = By.cssSelector("h1[id='cart_title']");
	private static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//p[contains(@class,'cart_navigation')]//a[@title=\"Proceed to checkout\"]");
	
	
	public ShoppingCartDetailPage(WebDriver driver) {
		super(driver);
	}
	
	
	public DeliveryAddressPage goToDeliveryAddressPage()
	{
		clickOn(PROCEED_TO_CHECKOUT_BUTTON);
		return new DeliveryAddressPage(getDriver());
	}
	

}
