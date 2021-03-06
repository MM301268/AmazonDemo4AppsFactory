/**
 * Includes all web elements and methods for Amazon's cart page
 *
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus.AmazonPages;

import com.meier.markus.HelperClasses.AmazonProduct;
import com.meier.markus.HelperClasses.ErrorHandler;
import com.meier.markus.HelperClasses.StrHlp;
import com.microsoft.playwright.Page;

public class AmazonCartPage {

  /**
   * Class properties repository for web element detection
   *
   * @Class property SELECTOR_AMOUNT Locator for selecting the amount of product to order
   * @Class property SELECTOR_NAME Locator for product name
   * @Class property SELECTOR_NULL Locator amount 0 products to order (delete)
   * @Class property SELECTOR_PRICE Locator product price
   * @Class property page Object of playwright where most of methods are executed against
   */
  private static final String SELECTOR_AMOUNT = "span[id='a-autoid-1-announce']";
  private static final String SELECTOR_NAME =
      "//*[@class='a-size-medium sc-product-title a-text-bold']";
  private static final String SELECTOR_NULL = "li[role='option'] >> text='0 (L�schen)'";
  private static final String SELECTOR_PRICE =
      "//*[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']";
  private Page page;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonCartPage(Page page) {
    this.page = page;
  }

  /**
   * Get product details for checking against original order
   * 
   * @return AmazonProduct Defines product in the shopping cart
   */
  public AmazonProduct getProductDetails() {
    try {
      AmazonProduct amazonProduct = new AmazonProduct();
      amazonProduct.setStrPrice(page.textContent(SELECTOR_PRICE));
      amazonProduct.setStrPrice(
          amazonProduct.getStrPrice().substring(0, amazonProduct.getStrPrice().length() - 2));
      amazonProduct.setName(page.textContent(SELECTOR_NAME));
      amazonProduct.setName(amazonProduct.getName().replace("\n", StrHlp.EMPTY_STRING));
      amazonProduct.setName(amazonProduct.getName().replace("  ", StrHlp.EMPTY_STRING));
      if ((amazonProduct.getStrPrice() != StrHlp.EMPTY_STRING)
          && (amazonProduct.getStrPrice() != null))
        amazonProduct.setPrice(Double.parseDouble((amazonProduct.getStrPrice().replace(",", "."))));
      return amazonProduct;
    } catch (NumberFormatException e) {
      ErrorHandler.markTestCaseAsFailed(e);
      return null;
    }
  }

  /**
   * Removes single item for shopping cart
   */
  public void removeSelectedItemFromCart() {
    try {
      page.click(SELECTOR_AMOUNT);
      page.click(SELECTOR_NULL);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

}
