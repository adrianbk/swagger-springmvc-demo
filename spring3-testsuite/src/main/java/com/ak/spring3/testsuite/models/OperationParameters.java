package com.ak.spring3.testsuite.models;

import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class OperationParameters {

   @RequestMapping(value = {"/operations/parameters/allowableRange/{id}"}, method = GET, produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public String allowableRange(
         @ApiParam(defaultValue = "1", allowableValues = "range[1, 99]")
         @PathVariable Integer id) {
      return "";
   }

   @RequestMapping(value = {"/operations/parameters/allowableValues/{id}"}, method = GET, produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public String allowableValues(
         @ApiParam(defaultValue = "1", allowableValues = "1,2,3,4,5")
         @PathVariable Integer id) {
      return "";
   }

   @RequestMapping(value = {"/operations/parameters/data-types"}, method = GET, produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   public String dataTypes(
         @RequestParam @ApiParam() String s,
         @RequestParam @ApiParam() Integer id,
         @RequestParam @ApiParam() Float f,
         @RequestParam @ApiParam() Short sh,
         @RequestParam @ApiParam() BigDecimal d,
         @RequestParam @ApiParam() Double myDouble,
         @RequestParam @ApiParam() Date date,
         @RequestParam @ApiParam Byte b) {
      return "";
   }

}
