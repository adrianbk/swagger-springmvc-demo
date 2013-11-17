package com.ak.swaggermvc.demo;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

   @Autowired
   private List<HandlerMapping> handlerMappings;


   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String printWelcome(ModelMap model) {

      model.addAttribute("message", "Hello world!");
      return "hello";
   }

   @RequestMapping(value = {"/pets", "/animals"}, method = RequestMethod.GET)
   public String m1(ModelMap model) {return "";}
   @RequestMapping(value = "/pets/dogs", method = RequestMethod.GET)
   public String m2(ModelMap model) {return "";}
   @RequestMapping(value = "/pets/cats", method = RequestMethod.GET)
   public String m3(ModelMap model) {return "";}
   @RequestMapping(value = "/vets", method = RequestMethod.GET)
   public String m4(ModelMap model) {return "";}
   @RequestMapping(value = "/vets/{id}", method = RequestMethod.GET)
   public String m5(ModelMap model) {return "";}


}