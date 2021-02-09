/**
 * Includes all web elements and methods for Amazon's item page
 * 
 * @author Markus Meier
 * @version 1.0
 *
 */
package com.meier.markus.AmazonPages;

import com.microsoft.playwright.Page;

public class AmazonNewItemsPage {

  /**
   * Class properties repository for web element detection
   *
   * @Class property LOCATOR_CART_BUTTON Locator for cart button
   */
  private final static String LOCATOR_CART_BUTTON = "#hlb-view-cart-announce";

  /**
   * Clicks on shopping card button
   */
  public static void clickShoppingCartButton(Page page) {
    try {
      page.click(LOCATOR_CART_BUTTON);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
