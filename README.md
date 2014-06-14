swagger-springmvc-demo
======================

A set of apps that test or demo swagger-spring mvc https://github.com/martypitt/swagger-springmvc/


### Building
```
./gradlew build
```

- If using intellij ues file >> open and point to the build.gradle file
- As of May 4 2014 there is a bug with intellij and gradle 1.12 - the intellij EAP has a fix so use the EAP version


#### shared-api
Some basic spring controllers and models shared with the other sub projects

#### spring3-music
Sample spring-web-mvc 3+ project with spring JavaConfig 
- uses @EnableSwagger for out of the box swagger-springmvc support
- imports the swagger-ui static content using a webjar `"org.ajar:swagger-spring-mvc-ui:0.2"`
- uses the controllers and models from 'shared-api'
- uses spring ResourceHandlers and a ViewResolver to serve the swagger-ui content form a webjar

#### spring3-testsuite
- spring-web-mvc 3+ project using spring xml configuration (mvc-dispatcher-servlet.xml)
- uses @EnableSwagger with SwaggerSpringMvcPlugin for a more customized swagger-springmvc configuration.
- demonstrates oauth2 configuration
- automated browser based tests in 'src/acceptanceTest'
- functional api level tests in 'src/functionalTest'

#### spring-boot
- A spring boot (spring 4.x ) application
- Three annotations and a single line of executable code for a fully running web app with swagger enabled.

#### swagger-static
- Spring boot app which serves static json files to swagger-ui
- Primarily a manual testing tool to verify how swagger-ui behaves with different json variants.

#### swagger-ui
- builds a webjar from swagger-ui's static content
- sdoc.jsp is the packaged into the jar, when rendered the swagger explore field should  be 
pre-populated with "<your-context-path>/api-docs"

.
