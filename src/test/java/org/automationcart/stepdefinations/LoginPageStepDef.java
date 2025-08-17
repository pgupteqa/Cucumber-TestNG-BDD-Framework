package org.automationcart.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationcart.cucumberHooks.Hooks;
import org.automationcart.pages.HomePage;
import org.automationcart.pages.LoginPage;
import org.automationcart.pages.MyAccountPage;

import static org.testng.AssertJUnit.assertEquals;

public class LoginPageStepDef {

    private final Hooks hooks;
    private HomePage homepage;
    private LoginPage loginpage;
    private MyAccountPage myAccountpage;

    public LoginPageStepDef(Hooks hooks)
    {
        this.hooks = hooks;
        this.homepage = hooks.getHomePage();

    }

    @Given("I am on the application login page")
    public void i_am_on_the_application_login_page()
    {
        loginpage = homepage.goToLoginPage();
    }

    @When("^I login with email (.+) and password (.+)$")
    public void i_login_with_email_and_password(String email, String password)
    {
        myAccountpage = loginpage.doLoginWith(email,password);
        hooks.setMyAccountPage(myAccountpage);
    }

    @When("^I login with Invalid email (.+) and password (.+)$")
    public void i_login_with_invalid_email_and_password(String email, String password)
    {
        loginpage.doLoginWithInvalidCredentials(email,password);
    }

    @Then("I should be logged in successfully and sees the username {string}")
    public void i_should_be_logged_In_successfully(String expectedUsername)
    {
        assertEquals(expectedUsername, myAccountpage.getUsername());
    }

    @Then("I should see the Login Error Message {string}")
    public void i_should_see_the_login_error_message(String expectedUsername)
    {
        assertEquals(expectedUsername, loginpage.getErrorMessage());
    }

}
