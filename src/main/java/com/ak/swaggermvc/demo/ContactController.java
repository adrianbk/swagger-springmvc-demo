package com.ak.swaggermvc.demo;

import com.ak.swaggermvc.demo.models.ContactApiDto;
import com.ak.swaggermvc.demo.models.PagedApiDto;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {


   @ApiOperation(value = "Gets all of the contacts for a business", response = ContactApiDto.class)
   @ResponseBody
   @RequestMapping(value = "/contacts", method = {RequestMethod.GET})
   public PagedApiDto<ContactApiDto> getContactsForBusiness(
      @PathVariable Integer businessId,
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {

      return null;
   }

}
