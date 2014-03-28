package com.ak.swaggermvc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Import({SwaggerConfig.class})
@ComponentScan(basePackages = {"com.mangofactory.swagger.controllers", "com.mangofactory.swagger.configuration", "com.ak.swaggermvc.demo"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/swaggerBusiness").setViewName("swaggerBusiness");
        registry.addViewController("/swaggerRelative").setViewName("swaggerRelative");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver result = new InternalResourceViewResolver();
        result.setViewClass(JstlView.class);
        result.setPrefix("/WEB-INF/pages/");
        result.setSuffix(".jsp");
        return result;
    }    
    
    // Maps resources path to webapp/resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("o2c.html").addResourceLocations("/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
        // map all static resources coming to '/usage/**' to the resource files under the 'swagger' directory
        ResourceHandlerRegistration registration = registry.addResourceHandler("/usage/**");
        registration.addResourceLocations("classpath:swagger/");
    }


}
