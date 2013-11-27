package com.ak.swaggermvc.demo;


import com.ak.swaggermvc.demo.models.Business;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//@RequestMapping(value = "/test")
//@Controller
public class TestController {

   @ApiOperation(value = "summary", httpMethod = "GET", position = 1, notes = "some detail on the endpoint")
   @RequestMapping(value = "position1", method = RequestMethod.GET)

   public void position2() {
   }


   @ApiOperation(value = "summary", position = 0, httpMethod = "GET", notes = "some detail on the endpoint")
   @RequestMapping(value = "position0", method = RequestMethod.GET)
   public void position1() {
   }

   @RequestMapping(value = "springMediaTypes", method = RequestMethod.GET,
                   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public void springProducesMediaTypes() {
   }

   @ApiOperation(value = "swaggerMediaTypes", produces = "application/xml, application/json, application/pdf",
                 consumes= "application/xml, application/json, application/pdf ")
   @RequestMapping(value = "swaggerMediaTypes", method = RequestMethod.GET,
                   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public void swaggerMediaTypes() {
   }

//   @RequestMapping(value = {"{businessId:\\d+}", "{businessId:\\w+}"}, method = GET)
//   @ResponseBody
//   public Business getBusiness(@PathVariable("businessId") String businessId) {
//      Business business = new Business();
//      business.name = "Widgets Ltd.";
//      return business;
//   }

}
