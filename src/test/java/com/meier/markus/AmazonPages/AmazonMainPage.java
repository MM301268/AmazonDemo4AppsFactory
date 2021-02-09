/**
 * Includes all web elements and methods for Amazon main page
 * 
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus.AmazonPages;

import com.meier.markus.HelperClasses.EncryptionWorker;
import com.meier.markus.HelperClasses.ErrorHandler;
import com.microsoft.playwright.Page;

public class AmazonMainPage {

  /**
   * Class properties repository for web element detection
   *
   * @Class property ACCEPT_COKKIE_BUTTON Locator for cookies acceptor button
   * @Class property CONTINUE_BUTTON Locator for button continue after user id input
   * @Class property GLOBAL_SEARCH_FIELD Locator for Amazon's search field
   * @Class property LATER_TEXT Later text for providing phone numbers
   * @Class property PASSWORD Locator for text field password
   * @Class property SEARCH_BUTTON Locator for button fires a search
   * @Class property SIGNIN_BUTTON Locator for sign on button
   * @Class property SIGNIN_SUBNMIT_BUTTON Locator for button sign in
   * @Class property TEST_OBJECT_URL URL of test object
   * @Class property USER_ID Locator for text field user id
   * @Class property page Object of playwright where most of methods are executed against
   */
  private static final String ACCEPT_COKKIE_BUTTON = "#sp-cc-accept";
  private static final String CONTINUE_BUTTON = "#continue";
  private static final String GLOBAL_SEARCH_FIELD = "#twotabsearchtextbox";
  private static final String LATER_TEXT = "text=/.*Später.*/";
  private static final String PASSWORD = "#ap_password";
  private static final String SEARCH_BUTTON = "#nav-search-submit-button";
  private static final String SIGNIN_BUTTON = "#nav-signin-tooltip";
  private static final String SIGNIN_SUBNMIT_BUTTON = "#signInSubmit";
  private static final String TEST_OBJECT_URL = "https://www.amazon.de";
  private static final String USER_ID = "#ap_email";
  private Page page;

  /**
   * Constructor
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public AmazonMainPage(Page page) {
    this.page = page;
  }

  /**
   * Get rid of the cookies dialog
   */
  public void acceptCookies() {
    try {
      if (page.querySelector(ACCEPT_COKKIE_BUTTON) != null)
        page.click(ACCEPT_COKKIE_BUTTON);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Login for User
   */
  public void login(String userIdValue, String passWordValue) {
    try {
      page.click(SIGNIN_BUTTON);
      page.fill(USER_ID, userIdValue);
      page.click(CONTINUE_BUTTON);
      page.fill(PASSWORD, EncryptionWorker.decrypt2String(passWordValue));
      page.click(SIGNIN_SUBNMIT_BUTTON);

      if (page.querySelector(LATER_TEXT) != null)
        page.click(LATER_TEXT);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

  /**
   * Navigates the browser to Amazon's main page
   */
  public void navigate() {
    try {
      page.navigate(TEST_OBJECT_URL);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

  /**
   * searches for items on Amazon's main page
   */
  public void searchForItem(String searchItem) {
    try {
      page.click(GLOBAL_SEARCH_FIELD);
      page.type(GLOBAL_SEARCH_FIELD, searchItem);
      page.click(SEARCH_BUTTON);
    } catch (Exception e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }
}
