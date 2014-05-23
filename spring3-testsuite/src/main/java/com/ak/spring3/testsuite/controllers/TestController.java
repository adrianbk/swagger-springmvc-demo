package com.ak.spring3.testsuite.controllers;


import com.fasterxml.jackson.annotation.JsonRawValue;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/test")
@Controller
public class TestController {

  @ResponseBody
  @ApiOperation(value = "summary", httpMethod = "GET", position = 1, notes = "some detail on the endpoint")
  @RequestMapping(value = "/position1", method = RequestMethod.GET)
  public String position2() {
    return "";
  }

  @ResponseBody
  @ApiOperation(value = "summary", position = 0, httpMethod = "GET", notes = "some detail on the endpoint")
  @RequestMapping(value = "/position0", method = RequestMethod.GET)
  public String position1() {
    return "";
  }

  @ResponseBody
  @RequestMapping(value = "/springMediaTypes", method = RequestMethod.GET,
          produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public String springProducesMediaTypes() {
    return "";
  }

  @ResponseBody
  @ApiOperation(value = "/swaggerMediaTypes", produces = "application/xml, application/json, application/pdf",
          consumes = "application/xml, application/json, application/pdf ")
  @RequestMapping(value = "/swaggerMediaTypes", method = RequestMethod.GET,
          produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public String swaggerMediaTypes() {
    return "";
  }

  @ResponseBody
  @RequestMapping("/allHttpMethods")
  public void allHttpMethods() {

  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public
  @ResponseBody
  String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
    return "{\"message\": \"success\"}";
  }

}
