package com.ak.swaggermvc.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/businesses")
@Controller
public class BusinessController implements ApplicationContextAware {

   private ApplicationContext ctx;

   @RequestMapping(method = RequestMethod.GET)
   public void list(){}

   @RequestMapping(value = "/{businessId:\\d+}")
   public String getBusiness(){
      return "got a business";
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.ctx = applicationContext;
   }
}
