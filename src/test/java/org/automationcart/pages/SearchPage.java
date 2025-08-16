package org.automationcart.pages;


import org.apache.logging.log4j.Logger;
import org.automationcart.utility.BrowserUtility;
import org.automationcart.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.NoSuchElementException;

public class SearchPage extends BrowserUtility {

	private static final By SEARCH_HEADING_LOCATOR = By.xpath("//span[@class='lighter']");
	private static final By ALL_PRODUCT_TITLE_NAME = By.xpath("//h5[@itemprop='name']/a");
	private Logger logger = LoggerUtility.getLogger(this.getClass());

	public SearchPage(WebDriver driver) {
		super(driver);

	}

	public String getSearchResultTitle() {
		return getVisibleText(SEARCH_HEADING_LOCATOR);
	}

	public boolean isSearchProductPresentInProductList(String searchTerm) {

		boolean isPresent = false;

		try {
			String lowerSearchTerm = searchTerm.toLowerCase();
			List<String> productNameList = getAllVisibleText(ALL_PRODUCT_TITLE_NAME);

			// info to display in the extent report
			logger.info("Search Term: " + searchTerm);
			logger.info("Product List: " + productNameList);

			isPresent = productNameList.stream().map(String::toLowerCase) // convert all product names to lower
																			// case
					.anyMatch(name -> name.contains(lowerSearchTerm)); // substring match

			if (isPresent) {
				logger.info("Search term found in product list." + searchTerm);
			} else {
				logger.warn("Search term NOT found in product list." + searchTerm);
			}

		}

		catch (NoSuchElementException e) {
			// Specific locator-related exceptions
			logger.error("No products found. Locator missing: " + e.getMessage());

		} catch (Exception e) {
			logger.error("Unexpected error while fetching product list: " + e.getMessage());

		}

		return isPresent;

		// Same logic using the For loop iterating to list

		/*
		 * for(String productName : productNameList) {
		 * if(productName.toLowerCase().contains(lowerSearchTerm)) { return true; } }
		 * 
		 * return false;
		 */

	}

	public ProductDetailPage clickOnTheProductTitle(int index) {
		logger.info("Open the Product Detail Page");
		clickOn(getAllElements(ALL_PRODUCT_TITLE_NAME).get(index));
		ProductDetailPage prodcutDetailPage = new ProductDetailPage(getDriver());
		return prodcutDetailPage;
	}

}
