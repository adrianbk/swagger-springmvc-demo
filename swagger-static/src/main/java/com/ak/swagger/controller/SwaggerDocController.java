package com.ak.swagger.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
public class SwaggerDocController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/api-docs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String resourceListing() throws IOException {
        String resourceListing = fileContents("resourceListing.json");
        return resourceListing;
    }


    @RequestMapping(value = "/api-docs/{fileName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String apiDeclaration(@PathVariable String fileName) throws IOException {
        String resourceListing = fileContents("apiDeclarations/" + fileName + ".json");
        return resourceListing;
    }

    private String fileContents(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        File file = classPathResource.getFile();
        return FileUtils.readFileToString(file);
    }
}
