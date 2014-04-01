package com.ak.swaggermvc.demo.models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


import java.util.List;

@ApiModel(description = "This is a description of the model an I do not show on the UI")
public class ContactApiDto extends ApiDto {

   
   @ApiModelProperty(required = true, value = "I should appear on the UI",  notes = "I am a model property note")
   protected String name;

   protected List<String> types;


   protected String firstName;


   protected String lastName;


   protected String phone;

   private String fax;

   private String position;


   private String notes;


   protected String mobile;


   protected String email;


   private String abn;

   private String ird;

   private boolean reportable;

   private boolean active;

   private boolean individual;

   @ApiModelProperty(required = false)
   private AddressApiDto shippingAddress;

   @ApiModelProperty(required = false)
   private AddressApiDto billingAddress;

   public ContactApiDto() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<String> getTypes() {
      return types;
   }

   public void setTypes(List<String> types) {
      this.types = types;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public boolean isReportable() {
      return reportable;
   }

   public void setReportable(boolean reportable) {
      this.reportable = reportable;
   }

   public boolean isActive() {
      return active;
   }

   public void setIsActive(boolean active) {
      this.active = active;
   }

   public boolean isIndividual() {
      return individual;
   }

   public void setIndividual(boolean individual) {
      this.individual = individual;
   }

   public AddressApiDto getShippingAddress() {
      return shippingAddress;
   }

   public void setShippingAddress(AddressApiDto shippingAddress) {
      this.shippingAddress = shippingAddress;
   }

   public AddressApiDto getBillingAddress() {
      return billingAddress;
   }

   public void setBillingAddress(AddressApiDto billingAddress) {
      this.billingAddress = billingAddress;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFax() {
      return fax;
   }

   public void setFax(String fax) {
      this.fax = fax;
   }

   public String getPosition() {
      return position;
   }

   public void setPosition(String position) {
      this.position = position;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public String getAbn() {
      return abn;
   }

   public void setAbn(String abn) {
      this.abn = abn;
   }

   public String getIrd() {
      return ird;
   }

   public void setIrd(String ird) {
      this.ird = ird;
   }
}