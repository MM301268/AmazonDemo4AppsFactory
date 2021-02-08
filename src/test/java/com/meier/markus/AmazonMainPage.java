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
   * @Class property testobjectURL => URL of test object
   * @Class property acceptCookieButton => Locator for cookies acceptor button
   * @Class property signinButton => Locator for sign on button
   * @Class property userId => Locator for text field user id
   * @Class property continueButton => Locator for button continue after user id input
   * @Class property passWord => Locator for text field password
   * @Class property signinBuitton => Locator for button sign in
   * @Class property laterTest => Later text for providing phone numbers
   * @Class property searchField => Locator for Amazon's search field
   * @Class property searchButton => Locator for button fires a search
   * @Class property accountLists => Locator for account details and Lists
   * @Class property signOut => Locator for sign out item in account detail and lists
   * 
   */
  private static final String testobjectURL = "https://www.amazon.de";
  private static final String acceptCookieButton = "#sp-cc-accept";
  private static final String signinButton = "#nav-signin-tooltip";
  private static final String userId = "#ap_email";
  private static final String continueButton = "#continue";
  private static final String passWord = "#ap_password";
  private static final String signinBuitton = "#signInSubmit";
  private static final String laterTest = "text=/.*Später.*/";
  private static final String searchField = "#twotabsearchtextbox";
  private static final String searchButton = "#nav-search-submit-button";
  private static final String accountLists = ".nav-long-width";
  private static final String signOut = "//a[@id='nav-item-signout']/span";


  /**
   * Get rid of the cookies dialog
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void acceptCookies(Page page) {
    try {
      if (page.querySelector(acceptCookieButton) != null)
        page.click(acceptCookieButton);
    } catch (Exception e) {
      // TODO Auto-generated catch block
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
      page.click(signinButton);
      page.fill(userId, userIdValue);
      page.click(continueButton);
      byte[] encryptedData = passWordValue.getBytes();
      page.fill(passWord, Decoder.decrypt2String(encryptedData));
      page.click(signinBuitton);

      if (page.querySelector(laterTest) != null)
        page.click(laterTest);
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
      page.hover(accountLists);
      page.click(signOut);
    } catch (Exception e) {
      // TODO Auto-generated catch block
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
      page.navigate(testobjectURL);
    } catch (Exception e) {
      // TODO Auto-generated catch block
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
      page.click(searchField);
      page.type(searchField, searchItem);
      page.click(searchButton);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
