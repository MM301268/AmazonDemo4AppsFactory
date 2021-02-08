package com.meier.markus;

import com.microsoft.playwright.Page;

public class ProductDetailsPage {

  private static final String ACCOUNT_DETAIL_LIST = ".nav-long-width";
  private static final String CART_BUTTON = "#add-to-cart-button";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";


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
   * Put Product to Cart
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void putProductToCart(Page page) {
    page.click(CART_BUTTON);
  }

}
