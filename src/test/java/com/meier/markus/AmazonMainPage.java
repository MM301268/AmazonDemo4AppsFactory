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
   * Class properties repository for web element detection
   *
   * @Class property TEST_OBJECT_URL => URL of test object
   * @Class property ACCEPT_COKKIE_BUTTON => Locator for cookies acceptor button
   * @Class property SIGNIN_BUTTON => Locator for sign on button
   * @Class property USER_ID => Locator for text field user id
   * @Class property CONTINUE_BUTTON => Locator for button continue after user id input
   * @Class property PASSWORD => Locator for text field password
   * @Class property SIGNIN_SUBNMIT_BUTTON => Locator for button sign in
   * @Class property LATER_TEXT => Later text for providing phone numbers
   * @Class property GLOBAL_SEARCH_FIELD => Locator for Amazon's search field
   * @Class property SEARCH_BUTTON => Locator for button fires a search
   * @Class property ACCOUNT_LIST => Locator for account details and Lists
   * @Class property SIGNOUT => Locator for sign out item in account detail and lists
   * 
   */
  private static final String TEST_OBJECT_URL = "https://www.amazon.de";
  private static final String ACCEPT_COKKIE_BUTTON = "#sp-cc-accept";
  private static final String SIGNIN_BUTTON = "#nav-signin-tooltip";
  private static final String USER_ID = "#ap_email";
  private static final String CONTINUE_BUTTON = "#continue";
  private static final String PASSWORD = "#ap_password";
  private static final String SIGNIN_SUBNMIT_BUTTON = "#signInSubmit";
  private static final String LATER_TEXT = "text=/.*Später.*/";
  private static final String GLOBAL_SEARCH_FIELD = "#twotabsearchtextbox";
  private static final String SEARCH_BUTTON = "#nav-search-submit-button";
  private static final String ACCOUNT_LIST = ".nav-long-width";
  private static final String SIGNOUT = "//a[@id='nav-item-signout']/span";


  /**
   * Get rid of the cookies dialog
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void acceptCookies(Page page) {
    try {
      if (page.querySelector(ACCEPT_COKKIE_BUTTON) != null)
        page.click(ACCEPT_COKKIE_BUTTON);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Login for User
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void login(Page page, String userIdValue, String passWordValue) {
    try {
      page.click(SIGNIN_BUTTON);
      page.fill(USER_ID, userIdValue);
      page.click(CONTINUE_BUTTON);
      page.fill(PASSWORD, EncryptionWorker.decrypt2String(passWordValue));
      page.click(SIGNIN_SUBNMIT_BUTTON);

      if (page.querySelector(LATER_TEXT) != null)
        page.click(LATER_TEXT);
    } catch (Exception e) {
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
      page.hover(ACCOUNT_LIST);
      page.click(SIGNOUT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Navigates the browser to Amazon's main page
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void navigate(Page page) {
    try {
      page.navigate(TEST_OBJECT_URL);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * searches for items on Amazon's main page
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void searchForItem(Page page, String searchItem) {
    try {
      page.click(GLOBAL_SEARCH_FIELD);
      page.type(GLOBAL_SEARCH_FIELD, searchItem);
      page.click(SEARCH_BUTTON);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
