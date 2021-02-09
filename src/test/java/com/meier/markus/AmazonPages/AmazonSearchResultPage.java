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
import com.microsoft.playwright.Page;

public class AmazonSearchResultPage {

  /**
   * Class properties repository for web element detection
   * 
   * @Class property SORT_SELECTOR => Locator for sorting search results
   * @Class property ITEM_SORT_ASC => Locator search item ASC
   * @Class property ACCOUNT_DETAIL_LIST => Locator for account details and Lists
   * @Class property SIGNOUT => Locator for sign out item in account detail and lists
   * @Class property PRODUCT_CONTAINER => Base path for the product container
   * @Class property PRODUCT_NAME_EXT => Extension to PRODUCT_CONTAINER for determine the product's
   *        name
   * @Class property EMPTY_STRING => Helper property for empty string
   */
  private static final String ACCOUNT_DETAIL_LIST = ".nav-long-width";
  private static final String EMPTY_STRING = "";
  private static final String ITEM_SORT_ASC = "#s-result-sort-select_1";
  private static final String PRODUCT_CONTAINER =
      "//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[X]/div/span/div";
  private static final String PRODUCT_NAME_EXT = "/div/div[2]/h2/a/span";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";
  private static final String SORT_SELECTOR = "//span[@id='a-autoid-0-announce']/span";

  /**
   * Determine the product name for Amazon product
   *
   * @param page => Object of playwright where most of methods are executed against
   * @return Name
   */
  private static String getProductName(Page page, String productContainerLocator) {
    try {
      String productNameLocater = productContainerLocator + PRODUCT_NAME_EXT;
      if (page.querySelector(productNameLocater) != null)
        return page.textContent(productNameLocater).toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  /**
   * Determine the product price for Amazon product
   *
   * @param page => Object of playwright where most of methods are executed against
   * @return Price as string!!!
   */
  private static String getProductPrice(Page page, String productContainerLocator) {
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
      e.printStackTrace();
      return null;
    }
    return null;
  }

  /**
   * Getting the details (name, price) for Amazon products returned by search criteria and sort
   *
   * @param page => Object of playwright where most of methods are executed against
   * @return List<AmazonProduct> => found first 5 based on search criteria and sort
   */
  public static List<AmazonProduct> getShoppingItemsDetails(Page page) {
    try {
      Thread.sleep(2000);
      List<AmazonProduct> listAmazonProduct = new ArrayList<AmazonProduct>();
      for (int i = 2; i <= 6; i++) {
        String actProductContainerLocator =
            PRODUCT_CONTAINER.replace("[X]", "[" + Integer.toString(i) + "]");
        AmazonProduct amazonProduct = new AmazonProduct();
        amazonProduct.setLocatorProductName(actProductContainerLocator + PRODUCT_NAME_EXT);
        amazonProduct.setName(getProductName(page, actProductContainerLocator));
        amazonProduct.setStrPrice(getProductPrice(page, actProductContainerLocator));
        if ((amazonProduct.getStrPrice() != EMPTY_STRING) && (amazonProduct.getStrPrice() != null))
          amazonProduct
              .setPrice(Double.parseDouble((amazonProduct.getStrPrice().replace(",", "."))));
        listAmazonProduct.add(amazonProduct);
      }
      return listAmazonProduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void clickOnCheapestItemAndPutInBasked(Page page, String locatorCheapestItem) {
    page.click(locatorCheapestItem);
  }

  /**
   * Logout for User
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void logout(Page page) {
    try {
      page.hover(ACCOUNT_DETAIL_LIST);
      page.click(SIGNOUT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Performs a sort of search results ASC
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void sortASC(Page page) {
    try {
      page.click(SORT_SELECTOR);
      page.click(ITEM_SORT_ASC);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
