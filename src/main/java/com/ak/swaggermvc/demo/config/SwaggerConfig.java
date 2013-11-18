package com.ak.swaggermvc.demo.config;

import com.mangofactory.swagger.configuration.JacksonScalaSupport;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.core.ControllerResourceGroupingStrategy;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.model.ApiKey;
import com.wordnik.swagger.model.AuthorizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Import(SpringSwaggerConfig.class)
public class SwaggerConfig {

   /*
   * Autowire wont work on generic collections - @Resource will
   * http://stackoverflow.com/questions/1363310/auto-wiring-a-list-using-util-schema-gives-nosuchbeandefinitionexception
   */
   @Resource(name = "defaultExcludeAnnotations")
   private List defaultExcludeAnnotations;

   @Autowired
   private ControllerResourceGroupingStrategy defaultControllerResourceGroupingStrategy;

   @Autowired
   private List<RequestMappingHandlerMapping> handlerMappings;

   @Bean
   /**
    * Adds the jackson scala module to the MappingJackson2HttpMessageConverter registered with spring
    */
   public JacksonScalaSupport jacksonScalaSupport(){
      JacksonScalaSupport jacksonScalaSupport = new JacksonScalaSupport();
      jacksonScalaSupport.setRegisterScalaModule(true);
      return jacksonScalaSupport;
   }

   @Bean
   @Autowired
   public SwaggerApiResourceListing swaggerApiResourceListing() {
      SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing();

      swaggerApiResourceListing.setApiInfo(apiInfo());
      swaggerApiResourceListing.setAuthorizationTypes(authorizationTypes());

      ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
      apiListingReferenceScanner.setHandlerMappings(handlerMappings);
      apiListingReferenceScanner.setExcludeAnnotations(defaultExcludeAnnotations);
      apiListingReferenceScanner.setControllerNamingStrategy(defaultControllerResourceGroupingStrategy);
      swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner);
      return swaggerApiResourceListing;
   }

   private List<AuthorizationType> authorizationTypes() {
      ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();
      authorizationTypes.add(new ApiKey("x-auth-token", "header"));
      return authorizationTypes;
   }

   private ApiInfo apiInfo() {
      ApiInfo apiInfo = new ApiInfo(
            "Demo Spring MVC swagger 1.2 api",
            "Sample spring mvc api based on the swagger 1.2 spec",
            "http://en.wikipedia.org/wiki/Terms_of_service",
            "somecontact@somewhere.com",
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0.html"
      );
      return apiInfo;
   }

   @Bean
   public Map<String, SwaggerApiResourceListing> swaggerApiResourceListingMap() {
      Map<String, SwaggerApiResourceListing> swaggerApiResourceListings = new
            LinkedHashMap<String, SwaggerApiResourceListing>();
      swaggerApiResourceListings.put("default", swaggerApiResourceListing());
      return swaggerApiResourceListings;
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
