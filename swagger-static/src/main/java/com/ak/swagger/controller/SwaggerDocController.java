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
    public String resourceListing(@RequestParam(value = "group", required = false, defaultValue = "groupA") String swaggerGroup) throws IOException {
        String resourceListing = fileContents(swaggerGroup, "resourceListing.json");
        return resourceListing;
    }

    @RequestMapping(value = "/api-docs/{group}/{apiDeclaration}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String apiDeclaration(@PathVariable String group, @PathVariable String apiDeclaration) throws IOException {
        return resourceListingFromDeclaration(group, apiDeclaration);
    }

    private String resourceListingFromDeclaration(String swaggerGroup, String apiDeclaration) throws IOException {
        return fileContents(swaggerGroup, "apiDeclarations/" + apiDeclaration + ".json");
    }

    private String fileContents(String swaggerGroup, String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(swaggerGroup + "/" + fileName);
        File file = classPathResource.getFile();
        return FileUtils.readFileToString(file);
    }
}
