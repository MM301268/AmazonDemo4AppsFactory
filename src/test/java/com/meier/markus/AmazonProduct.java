package com.meier.markus;

public class AmazonProduct {
  private String name, strPrice;
  private double price;
  private String locator;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStrPrice() {
    return strPrice;
  }

  public void setStrPrice(String strPrice) {
    this.strPrice = strPrice;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getLocator() {
    return locator;
  }

  public void setLocator(String locator) {
    this.locator = locator;
  }

}
