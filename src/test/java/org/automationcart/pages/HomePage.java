package org.automationcart.pages;

import org.automationcart.constants.Browser;
import org.automationcart.constants.Env;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.JSONUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.automationcart.constants.Env.QA;

public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(.,'Sign in')]");

    public HomePage(Browser browsername, Env enviornment, boolean isHeadless) {
        super(browsername, isHeadless);
        goToWebsite(JSONUtility.readJSON(enviornment).getUrl());
        maxWindow();
    }

    //This constructor is used for Lambdatest session
    public HomePage(WebDriver driver) {
        super(driver); // To call the parent class constructor from the child class constructor
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
        maxWindow();
    }

    public LoginPage goToLoginPage()
    {
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginpage = new LoginPage(getDriver());
        return loginpage;
    }
}
