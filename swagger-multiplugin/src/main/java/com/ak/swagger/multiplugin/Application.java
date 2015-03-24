package com.ak.swagger.multiplugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan(basePackages = {"com.ak.swaggerspringmvc.shared.controller"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Docket groupOnePlugin() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
              .paths(regex("/music.*?"))
              .build()
            .groupName("group1");
  }

  @Bean
  Docket group2Plugin() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select().paths(regex("/artists")).build()
            .groupName("group2");
  }
}