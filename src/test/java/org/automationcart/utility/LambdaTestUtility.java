package org.automationcart.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverlocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitieslocal = new ThreadLocal<DesiredCapabilities>();
	
	
	public static WebDriver initializeLambdaTest(String browsername, String scenario)
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browsername);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap();
        ltOptions.put("user", "pgbitbucket");
        ltOptions.put("accessKey", "LT_nsw51EEj2NYT2a24ycj5ZULyH6zwKnZ7HhOZPu674RYycVF");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", scenario);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        
        capabilitieslocal.set(capabilities);
        WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitieslocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        driverlocal.set(driver);
        
        return driverlocal.get();
	}
	
	public static void quitSession()
	{
		if(driverlocal.get() != null)
		{
			driverlocal.get().quit();
		}
	}

}
