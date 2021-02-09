/**
 * Includes all web elements and methods for Amazon's product details page
 * 
 * @author Markus Meier
 * @version 1.0
 *
 *
 */
package com.meier.markus.AmazonPages;

import com.microsoft.playwright.Page;

public class AmazonProductDetailsPage {

  /**
   * Class properties repository for web element detection
   *
   * @Class property CART_BUTTON Locator for cart button
   * @Class property page Object of playwright where most of methods are executed against
   */
  private static final String CART_BUTTON = "#add-to-cart-button";
  private Page page;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonProductDetailsPage(Page page) {
    this.page = page;
  }

  /**
   * Put Product to Cart
   */
  public void putProductToCart() {
    page.click(CART_BUTTON);
  }

}
