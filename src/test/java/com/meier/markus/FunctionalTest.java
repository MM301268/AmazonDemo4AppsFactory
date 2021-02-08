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
   * @Class property playWrightWorker => Instance of PlaywrightWorker
   */
  private Page page;
  private PlayWrightWorker playWrightWorker;

  /**
   * DoBeforSuite is fired at the very first beginning before any test case starts PlayWrightWorker
   * get instantiated and dimensions for browser are going to be set
   */
  @BeforeSuite
  private void doBeforeSuite() {
    try {
      playWrightWorker = new PlayWrightWorker();
      page = playWrightWorker.init();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Searches @Amazon for cheapest proposal of Snickers or Skittles
   */
  @Test(dataProvider = "provideSearchItemData")
  private void searchForItemAndPutItemAndCheckTest(String userId, String passWord,
      String item2search) {
    try {
      AmazonMainPage.navigate(page);
      AmazonMainPage.login(page, userId, passWord);
      AmazonMainPage.acceptCookies(page);
      AmazonMainPage.searchForItem(page, item2search);
      SearchResultPage.sortASC(page);
      SearchResultPage.logout(page);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Is fired after each test case had been performed Closes context and browser
   */
  @AfterSuite
  private void doAfterSuite() {
    try {
      playWrightWorker.terminate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Provides test data to searchForSnickersAndPutItemAndCheckTests
   *
   * @return String which includes in the first run snickers and in the second skittles
   */
  @DataProvider(name = "provideSearchItemData")
  public Object[][] item2seachData() {
    Object[][] data = new Object[2][3];

    data[0][0] = "Markus.Meier301268.MM@gmail.com";
    data[0][1] = "kBK¬“f°.=Œ¥©Ííç=";
    data[0][2] = "Snickers";
    data[1][0] = "Markus.Meier301268.MM@gmail.com";
    data[1][1] = "kBK¬“f°.=Œ¥©Ííç=";
    data[1][2] = "Skittles";

    return data;
  }
}
