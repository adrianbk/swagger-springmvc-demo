package com.ak.swaggermvc.demo.models;

import org.joda.time.LocalDate;

import java.util.Date;



public class Business {
   //not private - just for testing
   public int _id;
   public String _name;
   public String _owner;
   public LocalDate _inception;
   public BusinessType _businessType = BusinessType.PRODUCT;
   public Date _taxDate = new Date();

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
}


