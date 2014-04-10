package com.ak.swaggermvc.demo;

import com.ak.swaggermvc.demo.models.ContactApiDto;
import com.ak.swaggermvc.demo.models.ExtendedContactApiDto;
import com.ak.swaggermvc.demo.models.PagedApiDto;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Controller
@Api(value = "Contacts")
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

  @ApiOperation(value = "Creates an extended contact", notes = "Request body of of ExtendedContactApiDto which not a returned model")
  @ResponseBody
  @RequestMapping(value = "/contacts/extended", method = {RequestMethod.POST})
  public ResponseEntity createAContact(@PathVariable Integer businessId, @RequestBody ExtendedContactApiDto
          extendedContactApiDto) {
        return new ResponseEntity(HttpStatus.OK);
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

    @ApiOperation(value = "Gets a map of all of the contacts for a business" )
    @ResponseBody
    @RequestMapping(value = "/contacts/map/{businessId}", method = {RequestMethod.GET})
    public Map<String, ContactApiDto> getContactsMap(
            @PathVariable Integer businessId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return null;
    }

    @ApiOperation(value = "Update a map of all of the contacts for a business" )
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/contacts/map/{businessId}", method = {RequestMethod.POST})
    public void getContactsMap(
            @PathVariable Integer businessId,
            Map<String, ContactApiDto> contacts) {

    }
}
