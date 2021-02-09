/**
 * Includes all web elements and methods for Amazon's item page
 * 
 * @author Markus Meier
 * @version 1.0
 *
 */
package com.meier.markus.AmazonPages;

import com.meier.markus.HelperClasses.ErrorHandler;
import com.microsoft.playwright.Page;

public class AmazonNewItemsPage {

  /**
   * Class properties repository for web element detection
   *
   * @Class property LOCATOR_CART_BUTTON Locator for cart button
   * @Class property page Object of playwright where most of methods are executed against
   */
  private final static String LOCATOR_CART_BUTTON = "#hlb-view-cart-announce";
  private Page page;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonNewItemsPage(Page page) {
    this.page = page;
  }

  /**
   * Clicks on shopping card button
   */
  public void clickShoppingCartButton() {
    try {
      page.click(LOCATOR_CART_BUTTON);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

}
