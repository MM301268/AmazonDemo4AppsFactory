/**
 * Main test class consists of two testcases One look @Amazon for Snickers, the other for Skittles
 *
 * @author Markus Meier
 * @version 1.0
 *
 *
 *          This work complies with the JMU Honor Code.
 */

package com.meier.markus;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.Page;

public class FunctionalTest {

  /**
   * @Class property page => Object of playwright where most of methods are executed against
   * @Class property pww => instance of PlaywrightWorker
   */
  private Page page;
  private PlayWrightWorker pww;

  /**
   * DoBeforSuite is fired at the very first beginning before any test case starts PlayWrightWorker
   * get instantiated and dimensions for browser are going to be set
   */
  @BeforeSuite
  private void doBeforeSuite() {
    try {
      pww = new PlayWrightWorker();
      page = pww.init();
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }

  }

  /**
   * Searches @Amazon for cheapest proposal of Snickers or Skittles
   */
  @Test(dataProvider = "provideSearchItemData")
  private void searchForItemAndPutItemAndCheckTest(String item2search) {
    try {
      AmazonMainPage.navigate(page);
      AmazonMainPage.login(page);
      AmazonMainPage.acceptCookies(page);
      AmazonMainPage.searchForItem(page, item2search);
      SearchResultPage.sortASC(page);
      SearchResultPage.logout(page);
      Thread.sleep(3000);
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

  /**
   * Is fired after each test case had been performed Closes context and browser
   */
  @AfterSuite
  private void doAfterSuite() {
    try {
      pww.terminate();
    } catch (Exception ex) {
      // TODO Auto-generated catch block
      System.out.println(ex.toString());
    }
  }

  /**
   * Provides test data to searchForSnickersAndPutItemAndCheckTests
   *
   * @return String which includes in the first run snickers and in the second kittles
   */
  @DataProvider(name = "provideSearchItemData")
  public Object[] item2seachData() {
    Object[] data = new Object[2];

    data[0] = "Snickers";
    data[1] = "Skittles";

    return data;
  }
}
