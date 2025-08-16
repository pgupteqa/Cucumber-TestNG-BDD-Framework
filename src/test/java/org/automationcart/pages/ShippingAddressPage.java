package org.automationcart.pages;


import org.automationcart.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingAddressPage extends BrowserUtility {

	
	private static final By TERMS_OF_SERVICE_CHECKBOX_LOCATOR = By.id("cgv");
	private static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//button[@name='processCarrier']");
	
	
	
	public ShippingAddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public PaymentPage goToPaymentPage()
	{
		scrollToElement(TERMS_OF_SERVICE_CHECKBOX_LOCATOR);
		selectCheckbox(TERMS_OF_SERVICE_CHECKBOX_LOCATOR);
		clickOn(PROCEED_TO_CHECKOUT_BUTTON);
		return new PaymentPage(getDriver());
	}
	

}
