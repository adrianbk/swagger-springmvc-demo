package com.ak.swaggermvc.demo.models;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(description = "This is a description of the model an I do not show on the UI")
public class ExtendedContactApiDto extends ContactApiDto {

   private String extraProperty;

  public String getExtraProperty() {
    return extraProperty;
  }

  public void setExtraProperty(String extraProperty) {
    this.extraProperty = extraProperty;
  }
}