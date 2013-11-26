package com.ak.swaggermvc.demo;

import com.ak.swaggermvc.demo.models.Business;
import com.wordnik.swagger.annotations.ApiOperation;
import org.joda.time.LocalDate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class BusinessController {

   @RequestMapping(method = RequestMethod.GET)
   public void list() {
   }

   @RequestMapping(value = {"{businessId:\\d+}", "{businessId:\\w+}"})
   @ResponseBody
   public Business getBusiness(@PathVariable("businessId") String businessId) {
      Business business = new Business();
      business.name = "Widgets Ltd.";
      return business;
   }

   @ApiOperation(value = "Value is the summary",
                 notes = "Gives more detailed info on the api operation")
   @RequestMapping(value = {"withHttpMethodOverride"}, method = RequestMethod.GET)
   @ResponseBody
   public Business getBusinessWithHttpMethod() {
      Business business = new Business();
      business.name = "HTTP Widgets Ltd.";
      business.inception = LocalDate.now();
      return business;
   }

}
