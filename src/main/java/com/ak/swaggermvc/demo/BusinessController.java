package com.ak.swaggermvc.demo;

import com.ak.swaggermvc.demo.models.Business;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.joda.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class BusinessController {

   static AtomicInteger businessIdCount = new AtomicInteger(1);
   static Map<Integer, Business> businessCache = newHashMap();

   static{
      int businessId = businessIdCount.getAndIncrement();
      Business business = new Business();
      business.name = "HTTP Widgets Ltd.";
      business.inception = LocalDate.now();
      business.id = businessId;
      businessCache.put(businessId, business);
   }

   @ApiOperation(value = "get a list of businesses", notes = "Returns all businesses in the cache")
   @RequestMapping(value = "/businesses", method = GET)
   @ResponseBody
   public List<Business> list(HttpServletRequest request) {
      return new ArrayList<Business>(businessCache.values());
   }

   @ApiOperation(value = "Value is the summary", notes = "Gives more detailed info on the api operation")
   @RequestMapping(value = {"/businesses/{businessId}"}, method = GET, produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public Business getBusinessWithHttpMethod(
         @ApiParam(defaultValue = "1", value = "The id of the business to return")
         @PathVariable Integer businessId) {
      return businessCache.get(businessId);
   }

   @RequestMapping(value = {"/businesses"}, method = POST, consumes = APPLICATION_JSON_VALUE , produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public ResponseEntity<Business> createBusiness(@RequestBody Business business){
      int newBusinessId = businessIdCount.getAndIncrement();
      business.id = newBusinessId;
      businessCache.put(business.id, business);
      ResponseEntity<Business> responseEntity = new ResponseEntity<Business>(business, OK);
      return responseEntity;
   }

}
