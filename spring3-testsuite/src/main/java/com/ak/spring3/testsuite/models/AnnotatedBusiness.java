package com.ak.spring3.testsuite.models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.LocalDate;

import java.util.Date;


@ApiModel(value = "An annotated version of the Business class")
public class AnnotatedBusiness {

   @ApiModelProperty(value = "unique identifier")
   private int id;

   @ApiModelProperty(value = "name of the business")
   private String name;

   @ApiModelProperty(value = "Who ownes the business")
   private String owner;

   @ApiModelProperty(value = "The date the business was opened")
   private LocalDate inception;

   @ApiModelProperty(value = "The type of business.")
   private BusinessType businessType = BusinessType.PRODUCT;

   @ApiModelProperty(value = "Something tax related.")
   private Date taxDate = new Date();

   public enum BusinessType {
      PRODUCT(1),
      SERVICE(2);
      private int value;

      private BusinessType(int value) {
         this.value = value;
      }

      public int getValue() {
         return value;
      }
   }

  @ApiModelProperty(value = "unique identifier", required = true)
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public LocalDate getInception() {
      return inception;
   }

   public void setInception(LocalDate inception) {
      this.inception = inception;
   }

   public BusinessType getBusinessType() {
      return businessType;
   }

   public void setBusinessType(BusinessType businessType) {
      this.businessType = businessType;
   }

   public Date getTaxDate() {
      return taxDate;
   }

   public void setTaxDate(Date taxDate) {
      this.taxDate = taxDate;
   }
}


