package com.ak.spring3.testsuite.controllers;

import com.ak.spring3.testsuite.models.Business;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//https://github.com/martypitt/swagger-springmvc/issues/497#issuecomment-63619922
@RestController
public class TestSpringsRestController {
   @RequestMapping(value = "/springsRestController", method = RequestMethod.GET)
   @ApiOperation(
           value = "Find a Thing",
           notes = "Returns a Thing")
   @ResponseBody
   public Business get() {
      return new Business();
   }
}
