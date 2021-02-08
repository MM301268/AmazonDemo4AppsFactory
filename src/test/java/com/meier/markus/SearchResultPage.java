/**
 * Includes all web elements and methods for Amazon's search result page
 *
 * @author Markus Meier
 * @version 1.0
 *
 *
 *          This work complies with the JMU Honor Code.
 */
package com.meier.markus;

import com.microsoft.playwright.Page;

public class SearchResultPage {

  /**
   * Class properties repository for web element detection
   * 
   * @Class property SORT_SELECTOR => Locator for sorting search results
   * @Class property ITEM_SORT_ASC => Locator search item ASC
   * @Class property ACCOUNT_DETAIL_LIST => Locator for account details and Lists
   * @Class property SIGNOUT => Locator for sign out item in account detail and lists
   * 
   */
  private static final String SORT_SELECTOR = "//span[@id='a-autoid-0-announce']/span";
  private static final String ITEM_SORT_ASC = "#s-result-sort-select_1";
  private static final String ACCOUNT_DETAIL_LIST = ".nav-long-width";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";

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
      // TODO Auto-generated catch block
      e.printStackTrace();
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
