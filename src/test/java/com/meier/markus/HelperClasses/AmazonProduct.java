/**
 * Provides data skeleton for object Amazon product
 *
 * @author Markus Meier
 * @version 1.0
 *
 *
 *          This work complies with the JMU Honor Code.
 */

package com.meier.markus.HelperClasses;

/**
 * @Class property name: product name
 * @Class property strPrice: product price as string
 * @Class property price: product price
 * @Class property locator: locator for product name
 */
public class AmazonProduct {
  private String name, strPrice;
  private double price;
  private String locatorProductName;

  /**
   * Getter method for product name
   * 
   * @return product name
   */
  public String getName() {
    return name;
  }

  /**
   * Setter method for product name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter method for product price as String
   * 
   * @return product price
   */
  public String getStrPrice() {
    return strPrice;
  }

  public void setStrPrice(String strPrice) {
    this.strPrice = strPrice;
  }

  /**
   * Getter method for product price
   * 
   * @return product price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Setter method for product price
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Getter method for locator of product name
   * 
   * @return product name
   */
  public String getLocatorProductName() {
    return locatorProductName;
  }

  /**
   * Setter method for locator of product name
   */
  public void setLocatorProductName(String locatorProductName) {
    this.locatorProductName = locatorProductName;
  }

}
