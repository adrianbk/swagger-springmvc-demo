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

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class BusinessController {

   @ApiOperation(value = "get a list of businesses", notes = "Gets 20 default businesses")
   @RequestMapping(value = "/businesses", method = GET)
   @ResponseBody
   public List<Business> list() {
      List<Business> businessList = newArrayList();
      for(int i = 0; i < 20; i++){
         businessList.add(new Business());
      }
      return businessList;
   }


   @ApiOperation(value = "Value is the summary", notes = "Gives more detailed info on the api operation")
   @RequestMapping(value = {"/businesses/1"}, method = GET, produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public Business getBusinessWithHttpMethod() {
      Business business = new Business();
      business.name = "HTTP Widgets Ltd.";
      business.inception = LocalDate.now();
      return business;
   }

}
