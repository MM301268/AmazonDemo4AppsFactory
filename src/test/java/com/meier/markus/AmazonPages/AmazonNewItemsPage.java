package com.meier.markus.AmazonPages;

import com.microsoft.playwright.Page;

public class AmazonNewItemsPage {

  public static void clickShoppingCartButton(Page page) {
    try {
      page.click("#hlb-view-cart-announce");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
