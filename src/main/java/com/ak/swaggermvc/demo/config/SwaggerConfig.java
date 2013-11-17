package com.ak.swaggermvc.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.core.ControllerResourceGroupingStrategy;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import scala.reflect.BeanDescription;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Import(SpringSwaggerConfig.class)
public class SwaggerConfig {

   private List<Class<? extends Annotation>> defaultExcludeAnnotations;
   @Autowired
   private ControllerResourceGroupingStrategy defaultControllerResourceGroupingStrategy;

   @Autowired
   private List<RequestMappingHandlerMapping> handlerMappings;

   @Bean
   @Autowired
   public SwaggerApiResourceListing swaggerApiResourceListing() {
      SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing();
      ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
      apiListingReferenceScanner.setHandlerMappings(handlerMappings);
      apiListingReferenceScanner.setExcludeAnnotations(defaultExcludeAnnotations);
      apiListingReferenceScanner.setControllerNamingStrategy(defaultControllerResourceGroupingStrategy);
      swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner);
      return swaggerApiResourceListing;
   }

   @Bean
   public Map<String, SwaggerApiResourceListing> swaggerApiResourceListingMap(){
      Map<String, SwaggerApiResourceListing> swaggerApiResourceListings = new
            LinkedHashMap<String, SwaggerApiResourceListing>();
      swaggerApiResourceListings.put("default", swaggerApiResourceListing());
      return  swaggerApiResourceListings;
   }

//   def jackson2 = new MappingJackson2HttpMessageConverter()
//   ObjectMapper mapper = new ObjectMapper()
//   mapper.registerModule(new DefaultScalaModule())
//
//         jackson2.setObjectMapper(mapper)

//   <bean id="swaggerApiResourceListing" class="com.mangofactory.swagger.core.SwaggerApiResourceListing">
//   <property name="apiListingReferenceScanner">
//   <bean class="com.mangofactory.swagger.scanners.ApiListingReferenceScanner">
//   <property name="controllerNamingStrategy" ref="defaultControllerResourceGroupingStrategy"/>
//   </bean>
//   </property>
//
//   </bean>
}
