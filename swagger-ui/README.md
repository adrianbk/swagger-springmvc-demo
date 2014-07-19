##### swagger-spring-mvc-ui

- Downloads the swagger-ui code from github and adds its dist directory into the manifest of a jar file.
- Effectively a webjar which can be used by projects using swagger-springmvc.

The swagger ui version is controlled by the build.gradle file where `swaggerUiVersion` is the git tag of the
https://github.com/wordnik/swagger-ui repo which is packaged into the jar.
 
- Bundles a jsp file which should display the swagger UI at <host>/app/sdoc.jsp
- http://localhost:8080/sdoc.jsp
  
###### Usage
Maven
```xml
<repository>
    <id>oss-jfrog-artifactory</id>
    <name>oss-jfrog-artifactory-releases</name>
    <url>http://oss.jfrog.org/artifactory/oss-release-local</url>
</repository>

```

Gradle
```groovy
repositories {
    jcenter()
}
```

- Add the webjar as compile time dependency

```groovy
dependencies {

  ...
  compile "org.ajar:swagger-spring-mvc-ui:0.1"

}

```
##### Spring boot
- Include the web jar as dependency and the boot will take care of the rest

##### Spring web MVC
```java
import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_RESOURCE_PATTERNS;
import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_RESOURCE_LOCATION;
import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_VIEW_RESOLVER_PREFIX;
import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_VIEW_RESOLVER_SUFFIX;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ak.swaggerspringmvc.shared.controller", "com.ak.spring3.music"})
@EnableSwagger
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
}
```
