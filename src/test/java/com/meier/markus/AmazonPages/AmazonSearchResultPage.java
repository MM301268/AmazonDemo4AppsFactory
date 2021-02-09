/**
 * Includes all web elements and methods for Amazon's search result page
 *
 * @author Markus Meier
 * @version 1.0
 *
 *
 *          This work complies with the JMU Honor Code.
 */
package com.meier.markus.AmazonPages;

import java.util.ArrayList;
import java.util.List;
import com.meier.markus.HelperClasses.AmazonProduct;
import com.meier.markus.HelperClasses.ErrorHandler;
import com.meier.markus.HelperClasses.StrHlp;
import com.microsoft.playwright.Page;

public class AmazonSearchResultPage {

  /**
   * Class properties repository for web element detection
   * 
   * @Class property ITEM_SORT_ASC Locator search item ASC
   * @Class property PRODUCT_CONTAINER Base path for the product container
   * @Class property PRODUCT_NAME_EXT Extension to PRODUCT_CONTAINER for determine the product's
   *        name
   * @Class property SORT_SELECTOR Locator for sorting search results
   * @Class property page Object of playwright where most of methods are executed against
   */
  private static final String ITEM_SORT_ASC = "#s-result-sort-select_1";
  private static final String PRODUCT_CONTAINER =
      "//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[X]/div/span/div";
  private static final String PRODUCT_NAME_EXT = "/div/div[2]/h2/a/span";
  private static final String SORT_SELECTOR = "//span[@id='a-autoid-0-announce']/span";
  private Page page;
  private AmazonProduct amazonProduct;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonSearchResultPage(Page page) {
    this.page = page;
    amazonProduct = new AmazonProduct();
  }


  /**
   * Click on cheapest item identified by the locator
   * 
   * @param locatorCheapestItem Locator for cheapest item
   */
  public void clickOnCheapestItemAndPutInBasked(String locatorCheapestItem) {
    try {
      page.click(locatorCheapestItem);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

  /**
   * Determine the product name for Amazon product
   *
   * @param product ContainerLocator for building the locator for name web element
   * @return Name of product
   */
  private String getProductName(String productContainerLocator) {
    try {
      String productNameLocater = productContainerLocator + PRODUCT_NAME_EXT;
      if (page.querySelector(productNameLocater) != null)
        return page.textContent(productNameLocater).toString();
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
      return null;
    }
    return null;
  }

  /**
   * Determine the product price for Amazon product
   *
   * @param product ContainerLocator for building the locator for name web element
   * @return Price of product as !!!String!!!
   */
  private String getProductPrice(String productContainerLocator) {
    try {
      String productPriceLocater =
          productContainerLocator + "/div/div[3]/div/div/div/a/span[1]/span[2]/span[1]";
      if (page.querySelector(productPriceLocater) != null) {
        return page.textContent(productPriceLocater).toString();
      } else {
        productPriceLocater =
            productContainerLocator + "/div/div[4]/div/div/div/a/span[1]/span[2]/span[1]";
        if (page.querySelector(productPriceLocater) != null)
          return page.textContent(productPriceLocater).toString();
      }
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
      return null;
    }
    return null;
  }

  /**
   * Getting the details (name, price) for Amazon products returned by search criteria and sort
   *
   * @return List<AmazonProduct> => found first 5 based on search criteria and sort
   */
  public List<AmazonProduct> getShoppingItemsDetails() {
    try {
      Thread.sleep(2000);
      List<AmazonProduct> listAmazonProduct = new ArrayList<AmazonProduct>();
      for (int i = 2; i <= 6; i++) {
        String actProductContainerLocator =
            PRODUCT_CONTAINER.replace("[X]", "[" + Integer.toString(i) + "]");

        amazonProduct.setLocatorProductName(actProductContainerLocator + PRODUCT_NAME_EXT);
        amazonProduct.setName(getProductName(actProductContainerLocator));
        amazonProduct.setStrPrice(getProductPrice(actProductContainerLocator));
        if ((amazonProduct.getStrPrice() != StrHlp.EMPTY_STRING)
            && (amazonProduct.getStrPrice() != null))
          amazonProduct
              .setPrice(Double.parseDouble((amazonProduct.getStrPrice().replace(",", "."))));
        listAmazonProduct.add(amazonProduct);
      }
      return listAmazonProduct;
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
      return null;
    }
  }

  /**
   * Performs a sort of search results ASC
   */
  public void sortASC() {
    try {
      page.click(SORT_SELECTOR);
      page.click(ITEM_SORT_ASC);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }
}
