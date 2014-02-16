package com.ak.swaggermvc.demo;

import com.ak.swaggermvc.demo.models.ContactApiDto;
import com.ak.swaggermvc.demo.models.PagedApiDto;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
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

   @ApiOperation(value = "Creates a contact")
   @ResponseBody
   @RequestMapping(value = "/contacts", method = {RequestMethod.POST})
   public ResponseEntity createAContact(@PathVariable Integer businessId, @RequestBody ContactApiDto contactApiDto) {
      return null;
   }

   @ApiOperation(value = "Gets all of the contacts for a business without response class override" )
   @ResponseBody
   @RequestMapping(value = "/contacts/withoutRespClass/{businessId}", method = {RequestMethod.GET})
   public PagedApiDto<ContactApiDto> getContactsForBusinessWoAnn(
           @PathVariable Integer businessId,
           @RequestParam(required = false) Integer page,
           @RequestParam(required = false) Integer size) {

      return null;
   }

}
