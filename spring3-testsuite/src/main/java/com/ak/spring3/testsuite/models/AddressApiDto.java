package com.ak.spring3.testsuite.models;

public class AddressApiDto {
  private String addressLine1;
  private String addressLine2;
  private String suburb;
  private String state;
  private String postCode;
  private String country;

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountry() {
    return country;
  }
}
