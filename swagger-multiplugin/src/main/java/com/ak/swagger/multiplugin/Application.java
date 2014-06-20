package com.ak.swagger.multiplugin;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.config.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableSwagger
@ComponentScan(basePackages = {"com.ak.swaggerspringmvc.shared.controller"})
public class Application {

  @Autowired
  private SpringSwaggerConfig swaggerConfig;

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);
  }

  @Bean
  public SwaggerSpringMvcPlugin groupOnePlugin() {
    return new SwaggerSpringMvcPlugin(swaggerConfig)
            .includePatterns("/music.*?")
            .swaggerGroup("group1");
  }

  @Bean
  SwaggerSpringMvcPlugin group2Plugin() {
    return new SwaggerSpringMvcPlugin(swaggerConfig)
            .includePatterns("/artists")
            .swaggerGroup("group2");
  }
}