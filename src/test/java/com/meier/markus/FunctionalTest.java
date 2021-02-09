/**
 * Main test class consists of two testcases One look @Amazon for Snickers, the other for Skittles
 *
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.meier.markus.AmazonPages.AmazonCartPage;
import com.meier.markus.AmazonPages.AmazonGeneralPage;
import com.meier.markus.AmazonPages.AmazonMainPage;
import com.meier.markus.AmazonPages.AmazonNewItemsPage;
import com.meier.markus.AmazonPages.AmazonProductDetailsPage;
import com.meier.markus.AmazonPages.AmazonSearchResultPage;
import com.meier.markus.HelperClasses.AmazonProduct;
import com.meier.markus.HelperClasses.PlayWrightWorker;
import com.microsoft.playwright.Page;

public class FunctionalTest {

  /**
   * @Class property amazonProduct Helper property to information during findCheapestProduct
   * @Class property cartAmazonProduct Helper property to store product information out of Amazon
   *        cart page
   * @Class property cheapestAmazonProduct Helper property to store the cheapest product found in
   *        findCheapestProduct
   * @Class property listAmazonProducts List of returned amazon products based on search result page
   * @Class property page Object of playwright where most of methods are executed against
   * @Class property playWrightWorker Instance of PlaywrightWorker
   */
  private AmazonProduct amazonProduct;
  private AmazonProduct cartAmazonProduct = new AmazonProduct();
  private AmazonProduct cheapestAmazonProduct;
  private List<AmazonProduct> listAmazonProducts = new ArrayList<AmazonProduct>();
  private Page page;
  private PlayWrightWorker playWrightWorker;
  private AmazonMainPage amazonMainPage;
  private AmazonSearchResultPage amazonSearchResultPage;
  private AmazonProductDetailsPage amazonProductDetailsPage;
  private AmazonNewItemsPage amazonNewItemsPage;
  private AmazonCartPage amazonCartPage;
  private AmazonGeneralPage amazonGeneralPage;

  /**
   * DoBeforSuite is fired at the very first beginning before any test case starts PlayWrightWorker
   * get instantiated and dimensions for browser are going to be set and initiates the Amazon pages
   */
  @BeforeSuite
  private void doBeforeSuite() {
    try {
      playWrightWorker = new PlayWrightWorker();
      page = playWrightWorker.init();
      amazonMainPage = new AmazonMainPage(page);
      amazonSearchResultPage = new AmazonSearchResultPage(page);
      amazonProductDetailsPage = new AmazonProductDetailsPage(page);
      amazonNewItemsPage = new AmazonNewItemsPage(page);
      amazonCartPage = new AmazonCartPage(page);
      amazonGeneralPage = new AmazonGeneralPage(page);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * DoBeforTest clears List of Amazon products may have been filled by previous test cases and
   */
  @BeforeTest
  private void doBeforeTest() {
    try {
      listAmazonProducts.clear();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Searches @Amazon for cheapest proposal of products provided by data provider
   */
  @Test(dataProvider = "provideSearchItemData")
  private void searchForItemPutCartAndCheckTest(String userId, String passWord,
      String item2search) {
    try {
      amazonMainPage.navigate();
      amazonMainPage.login(userId, passWord);
      amazonMainPage.acceptCookies();
      amazonMainPage.searchForItem(item2search);
      amazonSearchResultPage.sortASC();
      listAmazonProducts = amazonSearchResultPage.getShoppingItemsDetails();
      findCheapestProduct();
      amazonSearchResultPage
          .clickOnCheapestItemAndPutInBasked(cheapestAmazonProduct.getLocatorProductName());
      amazonProductDetailsPage.putProductToCart();
      amazonNewItemsPage.clickShoppingCartButton();
      cartAmazonProduct = amazonCartPage.getProductDetails();
      assertEquals(cheapestAmazonProduct.getName(), cartAmazonProduct.getName());
      assertEquals(cheapestAmazonProduct.getPrice(), cartAmazonProduct.getPrice());
      amazonCartPage.removeSelectedItemFromCart();
      amazonGeneralPage.logout();
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
   * Iterate listAmazonProducts and find cheapest item
   */
  private void findCheapestProduct() {
    double compare_price = 9999.99;
    int cheapest_item = 0;
    try {
      for (int i = 0; i <= listAmazonProducts.size() - 1; i++) {
        amazonProduct = listAmazonProducts.get(i);
        if ((amazonProduct.getPrice() > 0.0) && (amazonProduct.getPrice() < compare_price)) {
          compare_price = amazonProduct.getPrice();
          cheapest_item = i;
        }
      }
      cheapestAmazonProduct = listAmazonProducts.get(cheapest_item);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Provides test data to searchForSnickersAndPutItemAndCheckTests
   *
   * @return String includes in the first run snickers and in the second skittles
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
