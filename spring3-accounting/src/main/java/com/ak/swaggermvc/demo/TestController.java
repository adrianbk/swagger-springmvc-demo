package com.ak.swaggermvc.demo;


import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/test")
@Controller
public class TestController {

   @ApiOperation(value = "summary", httpMethod = "GET", position = 1, notes = "some detail on the endpoint")
   @RequestMapping(value = "position1", method = RequestMethod.GET)
   public String position2() {
      return "";
   }

   @ApiOperation(value = "summary", position = 0, httpMethod = "GET", notes = "some detail on the endpoint")
   @RequestMapping(value = "position0", method = RequestMethod.GET)
   public String position1() {
      return "";
   }

   @RequestMapping(value = "springMediaTypes", method = RequestMethod.GET,
                   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public String springProducesMediaTypes() {
      return "";
   }

   @ApiOperation(value = "swaggerMediaTypes", produces = "application/xml, application/json, application/pdf",
                 consumes= "application/xml, application/json, application/pdf ")
   @RequestMapping(value = "swaggerMediaTypes", method = RequestMethod.GET,
                   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
   public String swaggerMediaTypes() {
      return "";
   }

   @RequestMapping("allHttpMethods")
   public void allHttpMethods(){

   }

}
