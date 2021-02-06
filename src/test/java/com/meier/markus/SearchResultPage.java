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
   * @Class property sortselector => Locator for sorting search results
   * @Class property acceptbutton => Locator search item ASC
   */
  private static final String sortselector = "//span[@id='a-autoid-0-announce']/span";
  private static final String itemasc = "#s-result-sort-select_1";

  /**
   * Performs a sort of search results ASC
   *
   * @param page => Object of playwright where most of methods are executed against
   */
  public static void sortASC(Page page) {
    page.click(sortselector);
    page.click(itemasc);
  }

}
