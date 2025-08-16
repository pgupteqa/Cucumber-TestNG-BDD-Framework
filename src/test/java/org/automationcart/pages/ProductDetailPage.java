package org.automationcart.pages;


import org.apache.logging.log4j.Logger;
import org.automationcart.constants.Size;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BrowserUtility {

	private static final By SIZE_DROP_DOWN_LOCATOR = By.id("group_1");
	private static final By ADD_TO_CART_BUTTON_LOCATOR = By.name("Submit");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//a[@title='Proceed to checkout']");
	private Logger logger= LoggerUtility.getLogger(this.getClass());
	
	public ProductDetailPage(WebDriver driver) {
		super(driver);
		
	}
	
	public ProductDetailPage changeSize(Size size) {
		logger.info("Select the Size of the Product");
		selectOptionFromDropDown(SIZE_DROP_DOWN_LOCATOR, size.toString());
		return new ProductDetailPage(getDriver());
	}
	
	public ProductDetailPage addProductToTheCart()
	{
		logger.info("Add to Cart button is visible and click on it");
		clickOn(ADD_TO_CART_BUTTON_LOCATOR);
		return new ProductDetailPage(getDriver());
	}
	
	public ShoppingCartDetailPage proceedToCheckout()
	{
		logger.info("click on proceed to checkout");
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ShoppingCartDetailPage(getDriver());
	}

}
