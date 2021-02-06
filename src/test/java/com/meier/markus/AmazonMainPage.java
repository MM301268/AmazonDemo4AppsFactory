/**
 * Includes all web elements and methods for Amazon main page
 * 
 * @author Markus Meier
 * @version 1.0
 *
 *
 *          This work complies with the JMU Honor Code.
 */

package com.meier.markus;

import com.microsoft.playwright.Page;

public class AmazonMainPage {

  /**
   * Class properties for web element detection
   *
   * @Class property testobjectrul => URL of test object
   * @Class property acceptbutton => Locator for cookies acceptor button
   * @Class property searchfield => Locator for Amazon's search field
   * @Class property searchbutton => Locator for button fires a search
   */
  private static final String testobjectrul = "https://www.amazon.de";
  private static final String acceptbutton = "#sp-cc-accept";
  private static final String searchfield = "#twotabsearchtextbox";
  private static final String searchbutton = "#nav-search-submit-button";

  /**
   * Get rid of the cookies dialog
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void acceptCookies(Page page) {
    if (page.querySelector(acceptbutton) != null)
      page.click(acceptbutton);
  }

  /**
   * Navigates the browser to Amazon's main page
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void navigate(Page page) {
    page.navigate(testobjectrul);
  }

  /**
   * searches for items on Amazon's main page
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void searchForItem(Page page, String searchItem) {
    page.click(searchfield);
    page.type(searchfield, searchItem);
    page.click(searchbutton);
  }
}
