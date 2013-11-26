package com.ak.swaggermvc.demo.models;

import org.joda.time.LocalDate;

import java.util.Date;


public class Business {
   //No getters or setters for testing purposes
   public String name;
   public String owner;
   public LocalDate inception;
   public Date taxDate = new Date();
}
