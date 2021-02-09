/**
 * Helper class which cares for playwright stuff and cares for dimension the browser window in the
 * correct size
 * 
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus.HelperClasses;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWrightWorker {

  /**
   * Class properties repository for web element detection
   * 
   * @Class property browser => instance of web browser
   * @Class property context => instance of web browser context
   */
  private static Browser browser;
  private static BrowserContext context;

  /**
   * Constructor instanciates DeviceManager to care for the correct size of browser window
   * Instanciates Playwright and creates browser and context
   */
  public PlayWrightWorker() {
    DeviceManager dm = new DeviceManager();
    dm.determineDisplaySize();
    int scrWidth = dm.getScreenSizeWidth();
    int scrHeight = dm.getScreenSizeHeight();
    Playwright playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().withHeadless(false));
    context = browser.newContext(new Browser.NewContextOptions().withViewport(scrWidth, scrHeight));
  }

  /**
   * Instanciates new page object
   */
  public Page init() {
    Page page = context.newPage();
    return page;
  }

  /**
   * Destructor of context and browser
   */
  public void terminate() {
    context.close();
    // browser.close();
  }

}
