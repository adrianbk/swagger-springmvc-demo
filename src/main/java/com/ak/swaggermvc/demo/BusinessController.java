package com.ak.swaggermvc.demo;

import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/businesses", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class BusinessController implements ApplicationContextAware {

   private ApplicationContext ctx;

   @RequestMapping(method = RequestMethod.GET)
   public void list(){}

   @RequestMapping(value = {"/{businessId:\\d+}", "/{businessId:\\w+}"})
    public String getBusiness(@PathVariable("businessId") String businessId){
      return "got a business again";
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.ctx = applicationContext;
   }
}
