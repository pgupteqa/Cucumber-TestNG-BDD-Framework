package org.automationcart.cucumberHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.automationcart.constants.Browser;
import org.automationcart.constants.Env;
import org.automationcart.pages.HomePage;
import org.automationcart.pages.MyAccountPage;
import org.automationcart.pojos.User;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LambdaTestUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class Hooks {

    protected HomePage homepage;
    protected MyAccountPage myAccountPage;
    //protected LoginPage loginPage;
    protected User user; // current user object
    //protected InvalidUsers invaliduser;
    private Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isUserLoggedIn = false;
    private boolean isLambdaTest;
    private boolean isHeadLess;
    private String browsername;
    private String enviornmentname;

    @Before
    public void setup(Scenario scenario) {

        browsername = System.getProperty("browsername", "chrome");
        enviornmentname = System.getProperty("envname", "QA");
        isHeadLess = Boolean.parseBoolean(System.getProperty("isHeadLess", "false"));
        isLambdaTest = Boolean.parseBoolean(System.getProperty("isLambdaTest", "false"));

        WebDriver lambdadriver;

        if (isLambdaTest) {

            lambdadriver = LambdaTestUtility.initializeLambdaTest(browsername, scenario.getName());
            homepage = new HomePage(lambdadriver);

        } else {
            // Runs on the local machine
            logger.info("Loads the HomePage of the website");
            homepage = new HomePage(Browser.valueOf(browsername.toUpperCase()),
                    Env.valueOf(enviornmentname.toUpperCase()), isHeadLess);
        }

        isUserLoggedIn = false;
    }

    public BrowserUtility getInstance() {
        return homepage;
    }

    public HomePage getHomePage(){
        return homepage;
    }

    public MyAccountPage getMyAccountPage(){ return myAccountPage; }

    public void setMyAccountPage(MyAccountPage myAccountPage) {
        this.myAccountPage = myAccountPage;
    }

    @After
    public void cucumber_tearDown(Scenario scenario) {
        try{
            if(scenario.isFailed())
            {
                //capture the screenshot
                String screenshotPath = homepage.takeScreenShot(scenario.getName().
                        replaceAll(" ","+"));

                File screenshotFile = new File(screenshotPath);

                // Attach screenshot to Cucumber report
                byte[] screenshotBytes = Files.readAllBytes(screenshotFile.toPath());
                scenario.attach(screenshotBytes, "image/png", screenshotBytes.toString());
            }
        }
        catch (Exception e) {
            logger.error("Error capturing screenshot: " + e.getMessage());
        }
        finally
        {
            if (isLambdaTest) {
                LambdaTestUtility.quitSession(); // quit the lambdatest browser session
            }
            else {
                homepage.quitBrowserSession(); // quit the local browser session
            }
        }
    }
}