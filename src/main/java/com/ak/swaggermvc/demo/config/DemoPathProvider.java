package com.ak.swaggermvc.demo.config;

import com.mangofactory.swagger.core.SwaggerPathProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;

public class DemoPathProvider implements SwaggerPathProvider {
    private SwaggerPathProvider defaultSwaggerPathProvider;
    @Autowired
    private ServletContext servletContext;


    @Override
    public String getApiResourcePrefix() {
        return defaultSwaggerPathProvider.getApiResourcePrefix();
    }

    public String getAppBasePath() {
        return UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:8097")
                .path(servletContext.getContextPath())
                .build()
                .toString();
    }

    @Override
    public String sanitizeRequestMappingPattern(String requestMappingPattern) {
        return defaultSwaggerPathProvider.sanitizeRequestMappingPattern(requestMappingPattern);
    }

    public void setDefaultSwaggerPathProvider(SwaggerPathProvider defaultSwaggerPathProvider) {
        this.defaultSwaggerPathProvider = defaultSwaggerPathProvider;
    }
}
