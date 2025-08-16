package org.automationcart.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.automationcart.constants.Browser;

// Its a Parent class that is used in the pages. And here we have used to abstraction, creating the reusable methods and used them inside the pages.

public abstract class BrowserUtility {

	private final Logger logger = LoggerUtility.getLogger(this.getClass());
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver.get(); // threadlocal is used so we have to get and set the driver. to assign a value
								// we have to use set method.
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // Initialize driver instance
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	// Constructor for browser management
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
				logger.info("Chrome Browser is selected in headless");
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
				logger.info("Chrome Browser is selected");
			}
		}

		else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
				logger.info("Firefox Browser is selected");
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
				logger.info("Firefox Browser is selected");
			}

		}

		else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
				logger.info("Microsoft Edge Browser is selected");
			}

		}

		else {
			logger.error("Invalid browsername..... Please select a valid browser");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Navigated to the Website " + url);
		driver.get().get(url);
	}

	public void maxWindow() {
		logger.info("Maximized the Browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding the Element with locator" + locator);

		// WebElement element = driver.get().findElement(locator);
		// synchromized method
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		logger.info("Found the Element with locator" + locator);
		logger.info("perform click action and clicked on the element" + locator);

		element.click();
	}

	public void clickOn(WebElement element) {

		logger.info("perform click action and clicked on the element" + element);
		element.click();
	}

	public void clearText(By locator) {
		logger.info("Finding the Element with locator" + locator);

		// WebElement element = driver.get().findElement(locator);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Found the Element with locator" + locator);

		element.clear();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding the Element with locator" + locator);

		// WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Found the Element with locator" + locator);
		logger.info("enter the text" + locator);
		element.sendKeys(textToEnter);
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding the Element with locator" + locator);

		// WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Found the Element with locator and now enter special key" + locator);
		element.sendKeys(keyToEnter);
	}

	public String getVisibleText(By locator) {
		// WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.getText();
	}

	// overload method to get visible text by webelement
	public String getVisibleText(WebElement element) {
		logger.info("Returns the visible text" + element.getText());
		return element.getText();
	}

	public List<String> getAllVisibleText(By locator) {

		List<String> visibleTextList = new ArrayList<String>();

		try {
			logger.info("Finding all Elements with the Locator" + locator);

			List<WebElement> elementList = driver.get().findElements(locator);
			if (elementList.isEmpty())
			{
				logger.warn("No elements found for locator:" + locator);
				System.err.println("Element not available on the Page " + locator);
				throw new NoSuchElementException("No elements found for locator: " + locator);
			}
			
			for (WebElement element : elementList) {
				logger.info("Element found now storing them in a list of string");
				String text = getVisibleText(element);
	            System.out.println("Element Text: [" + text + "]");
				visibleTextList.add(getVisibleText(element));
			}

		} catch (Exception e) {
			//System.err.println("Element not available on the Page " + e.getMessage());
			//e.printStackTrace();
			logger.error("Exception in getAllVisibleText(): " + e.getMessage());
	        throw e; // Propagate to the calling method
		}

		return visibleTextList;

	}

	// select from dropdown with select tag
	public void selectOptionFromDropDown(By dropdownlocator, String optionValueToSelect) {
		logger.info("Finding all Elements with the Locator" + dropdownlocator);

		WebElement element = driver.get().findElement(dropdownlocator);
		Select options = new Select(element);

		logger.info("Selecting the Option " + optionValueToSelect);

		options.selectByVisibleText(optionValueToSelect);
	}

	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding All Elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Elements Found and now printing the List of Elements");

		return elementList;

	}

	public void selectCheckbox(By locator) {
		logger.info("Finding all Elements with the Locator" + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element found and clicking on the checkbox");
		element.click();

	}

	public boolean isCheckboxSelected(By locator) {
		logger.info("Finding all Elements with the Locator" + locator);

		// WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		logger.info("Element found and clicking on the checkbox");

		boolean status = element.isSelected();

		if (status != true) {
			element.click();
			return status;
		} else {
			return status;
		}

	}

	public void scrollToElement(By locator) {
		logger.info("Scrolling to element using locator: " + locator);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean presenceOfLocator(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element.isDisplayed();

	}

	public void quitBrowserSession() {
		logger.info("Browser is Closed");
		driver.get().quit();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		String path = "./screenshot/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;
	}

    public byte[] takeScreenShotasByte(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        byte[] screenshotbyte = screenshot.getScreenshotAs(OutputType.BYTES);
        return screenshotbyte ;
    }

}
