###### swagger-static

- Downloads the swagger-ui code from github and adds its dist directory into the manifest of a jar file.
Effectively a webjar which can be used by projects using swagger-springmvc.

The swagger ui version is controlled by the build.gradle file where `swaggerUiVersion` is the git tag of the
https://github.com/wordnik/swagger-ui repo which is used to package the jar.
 
- Bundles a jsp file which should be display the swagger UI at <host>/app/sdoc.jsp
  
###### Usage
 
```
ext {
      swaggerUiVersion = '2.0.16'
...

Using the web jar:
"org.ajar:swagger-spring-mvc-ui:0.1"

Maven
```
<repository>
        <id>oss-jfrog-artifactory</id>
        <name>oss-jfrog-artifactory-releases</name>
        <url>http://oss.jfrog.org/artifactory/oss-release-local</url>
    </repository>

```

Gradle
```
repositories {
    jcenter()
}
```

##### Spring boot
- Include the web jar as dependency and the boot will take care of the rest

##### Spring web MVC
```
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