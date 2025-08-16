package org.automationcart.pages;

import org.apache.logging.log4j.Logger;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class DeliveryAddressPage extends BrowserUtility {

	
	private static final By ADDRESS_EQUAL_CHECKBOX_LOCATOR = By.id("addressesAreEquals");
	private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.xpath("//button[@name='processAddress']");
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public DeliveryAddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validateAddressEqualCheckboxIsSelected()
	{
		logger.info("Validate the delivery address checkbox is already checked by default");
		boolean checkbox_status = isCheckboxSelected(ADDRESS_EQUAL_CHECKBOX_LOCATOR);
		Assert.assertTrue(checkbox_status);
		return checkbox_status;
	}
	
	public ShippingAddressPage goToShippingAddressPage()
	{
		logger.info("Proceed through Address, Shippinig");
		boolean checkbox_status = isCheckboxSelected(ADDRESS_EQUAL_CHECKBOX_LOCATOR);
		Assert.assertTrue(checkbox_status);
		scrollToElement(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
		return new ShippingAddressPage(getDriver());
	}
	

}
