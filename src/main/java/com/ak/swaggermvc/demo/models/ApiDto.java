package com.ak.swaggermvc.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;


@JsonIgnoreProperties("links")
public class ApiDto extends ResourceSupport {
   private static final String INVALID_UID_MESSAGE = "is not valid";
   private static final String INVALID_UID_REGEX = "^[-]?[\\d]{1,9}$";


   private String uid;

   @JsonIgnore
   private String uri;

   public String getUid() {
      return uid;
   }

   public void setUid(String uid) {
      this.uid = uid;
   }

   public String getUri() {
      return uri;
   }

   public void setUri(String uri) {
      this.uri = uri;
   }
}
