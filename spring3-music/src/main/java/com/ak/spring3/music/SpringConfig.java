package com.ak.spring3.music;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ak.swaggerspringmvc.shared.controller", "com.ak.spring3.music"})
@EnableSwagger2
public class SpringConfig extends WebMvcConfigurerAdapter {

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(WEB_JAR_RESOURCE_PATTERNS)
              .addResourceLocations(WEB_JAR_RESOURCE_LOCATION).setCachePeriod(0);
   }

   @Bean
   public InternalResourceViewResolver getInternalResourceViewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix(WEB_JAR_VIEW_RESOLVER_PREFIX);
      resolver.setSuffix(WEB_JAR_VIEW_RESOLVER_SUFFIX);
      return resolver;
   }

   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }

   @Bean
   public Docket groupOnePlugin() {
      return new Docket(DocumentationType.SWAGGER_2)
              .directModelSubstitute(LocalDate.class, String.class)
              .directModelSubstitute(LocalDateTime.class, String.class);
   }
}
