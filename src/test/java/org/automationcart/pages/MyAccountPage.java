package org.automationcart.pages;

import org.apache.logging.log4j.Logger;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title='View my customer account']/span");
    private static final By SEARCH_TEXT_BOX_LOCATOR = By.id("search_query_top");
    private static final By MY_FIRST_ADDRESS_LOCATOR = By.xpath("//a[@title='Add my first address']");

    private Logger logger = LoggerUtility.getLogger(this.getClass());

    public MyAccountPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public String getUsername()
    {
        logger.info("Retrive the Account name");
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public SearchPage searchForProduct(String productName)
    {
        logger.info("Search for the product with search term as " +productName);
        enterText(SEARCH_TEXT_BOX_LOCATOR, productName);
        enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        SearchPage searchPage = new SearchPage(getDriver());
        return searchPage;

    }

    public boolean validateMyFirstAddressOption()
    {
        logger.info("check for the add new address locator");
        return presenceOfLocator(MY_FIRST_ADDRESS_LOCATOR);

    }

    public AddressPage goToAddressPage()
    {
        logger.info("Navigate to the New Address Page ");
        clickOn(MY_FIRST_ADDRESS_LOCATOR);
        return new AddressPage(getDriver());
    }
}
