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
   * @Class property sortselector => Locator for sorting search results
   * @Class property itemasc => Locator search item ASC
   * @Class property accountLists => Locator for account details and Lists
   * @Class property signOut => Locator for sign out item in account detail and lists
   * 
   */
  private static final String sortSelector = "//span[@id='a-autoid-0-announce']/span";
  private static final String itemAsc = "#s-result-sort-select_1";
  private static final String accountLists = ".nav-long-width";
  private static final String signOut = "//a[@id='nav-item-signout']/span";

  /**
   * Performs a sort of search results ASC
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void sortASC(Page page) {
    page.click(sortSelector);
    page.click(itemAsc);
  }

  /**
   * Logout for User
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void logout(Page page) {
    page.hover(accountLists);
    page.click(signOut);
  }

}
