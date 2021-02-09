package com.meier.markus.AmazonPages;

import com.meier.markus.HelperClasses.AmazonProduct;
import com.microsoft.playwright.Page;

public class AmazonCartPage {

  private static final String ACCOUNT_DETAIL_LIST = ".nav-long-width";

  private static final String EMPTY_STRING = "";
  private static final String SELECTOR_PRICE =
      "//*[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']";
  private static final String SELECTOR_AMOUNT = "span[id='a-autoid-1-announce']";
  private static final String SELECTOR_NAME =
      "//*[@class='a-size-medium sc-product-title a-text-bold']";
  private static final String SELECTOR_NULL = "li[role='option'] >> text='0 (Löschen)'";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";


  public static AmazonProduct getProductDetails(Page page) {
    try {
      AmazonProduct amazonProduct = new AmazonProduct();
      amazonProduct.setStrPrice(page.textContent(SELECTOR_PRICE));
      amazonProduct.setStrPrice(
          amazonProduct.getStrPrice().substring(0, amazonProduct.getStrPrice().length() - 2));
      amazonProduct.setName(page.textContent(SELECTOR_NAME));
      amazonProduct.setName(amazonProduct.getName().replace("\n", EMPTY_STRING));
      amazonProduct.setName(amazonProduct.getName().replace("  ", EMPTY_STRING));
      if ((amazonProduct.getStrPrice() != EMPTY_STRING) && (amazonProduct.getStrPrice() != null))
        amazonProduct.setPrice(Double.parseDouble((amazonProduct.getStrPrice().replace(",", "."))));
      return amazonProduct;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return null;
    }
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

  public static void removeSelectedItemFromCart(Page page) {
    page.click(SELECTOR_AMOUNT);
    page.click(SELECTOR_NULL);
  }

}
