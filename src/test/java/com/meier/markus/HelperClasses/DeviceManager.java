/**
 * Helper class for handling display size matters
 *
 * @author Markus Meier
 * @version 1.0
 *
 */

package com.meier.markus.HelperClasses;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

public class DeviceManager {

  /**
   * @Class property width => Display width
   * @Class property height => Display height
   */
  private int width, height;

  /**
   * Determines the dimensions of the display
   *
   */
  public void determineDisplaySize() {
    try {
      GraphicsDevice gd =
          GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      width = gd.getDisplayMode().getWidth();
      height = gd.getDisplayMode().getHeight();
    } catch (HeadlessException e) {
      ErrorHandler.markTestCaseAsFailed(e);
    }
  }

  /**
   * Getter method for display width
   *
   * @return Display width
   */
  public int getScreenSizeWidth() {
    return width;
  }

  /**
   * Getter method for display height
   *
   * @return Display height
   */
  public int getScreenSizeHeight() {
    return height;
  }
}
