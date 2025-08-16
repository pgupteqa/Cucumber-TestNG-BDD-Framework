package org.automationcart.pages;

import org.apache.logging.log4j.Logger;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {

    Logger logger= LoggerUtility.getLogger(this.getClass());

    //these values are not going to change so marked them with final keyword
    public static final By EMAIL_TEXT_LOCATOR = By.xpath("//input[@id='email']");
    public static final By PASSWORD_TEXT_LOCATOR = By.xpath("//input[@id='passwd']");
    public static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@id='SubmitLogin']");
    public static final By LOGIN_ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class,'alert-danger')]/ol/li");

    public LoginPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public MyAccountPage doLoginWith(String emailAddress, String password)
    {
        logger.info("enter the emailaddress as " +emailAddress+ " and password " +password+ " and click on sign in button");
        enterText(EMAIL_TEXT_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_LOCATOR, password );
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }

    public LoginPage doLoginWithInvalidCredentials(String emailAddress, String password)
    {
        logger.info("enter the emailaddress as " +emailAddress+ " and password " +password+ " and click on sign in button");
        enterText(EMAIL_TEXT_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_LOCATOR, password );
        clickOn(SIGN_IN_BUTTON_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }

    public String getErrorMessage()
    {
        return getVisibleText(LOGIN_ERROR_MESSAGE_LOCATOR);

    }


}
