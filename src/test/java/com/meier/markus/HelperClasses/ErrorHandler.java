/**
 * Error Handler
 *
 * @author Markus Meier
 * @version 1.0
 *
 */
package com.meier.markus.HelperClasses;

import org.testng.Assert;

public class ErrorHandler {

  /**
   * Logout for User
   *
   * @param Exception to print stack trace
   */
  public static void markTestCaseAsFailed(Exception e) {
    e.printStackTrace();
    Assert.fail();
  }

}
