/**
 * Logout can be done on most pages => therefore general page
 *
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus.AmazonPages;

import com.microsoft.playwright.Page;

public class AmazonGeneralPage {
  /**
   * Class properties repository for web element detection
   *
   * @Class property ACCOUNT_DETAIL_LIST Locator for account details and Lists
   * @Class property SIGNOUT Locator for sign out item in account detail and lists
   * @Class property page Object of playwright where most of methods are executed against
   */

  private static final String ACCOUNT_DETAIL_LIST = ".nav-long-width";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";
  private Page page;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonGeneralPage(Page page) {
    this.page = page;
  }

  /**
   * Logout for User
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public void logout() {
    try {
      page.hover(ACCOUNT_DETAIL_LIST);
      page.click(SIGNOUT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
