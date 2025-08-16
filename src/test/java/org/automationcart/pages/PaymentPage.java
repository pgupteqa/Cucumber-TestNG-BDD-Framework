package org.automationcart.pages;


import org.apache.logging.log4j.Logger;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BrowserUtility {

	
	private static final By PAYMENT_PAGE_HEADING_LOCATOR = By.xpath("//h1[@class='page-heading']");
	private static final By PAY_BY_BANK_WIRE_LOCATOR = By.xpath("//p[@class='payment_module']/a[@class='bankwire']");
	private static final By CONFIRM_ORDER_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'button')]/span[contains(text(),'I confirm my order')]");
	
	private static final By ALERT_ORDER_SUCCESS_MESSAGE = By.xpath("//p[contains(@class,'alert-success')]");
	private Logger logger= LoggerUtility.getLogger(this.getClass());
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String makePaymentByWire()
	{
		logger.info("Proceed to Payment and validate payment is successfull");
		clickOn(PAY_BY_BANK_WIRE_LOCATOR);
		clickOn(CONFIRM_ORDER_BUTTON_LOCATOR);
		return getVisibleText(ALERT_ORDER_SUCCESS_MESSAGE);	
	}

}
