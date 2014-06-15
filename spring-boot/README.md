spring-boot
===============

A spring boot app with swagger ui integrated.
- Lists boots endpoints: environment, health, etc.

```
./gradlew :spring-boot:bootRun
```
- http://localhost:8080/
- Uses the swagger-ui jar bundled by the accompanying swagger-ui project
- Opens the swagger petstore example so change the explore field to `http://localhost:8080/api-docs`


#### Configuration
```

@Configuration
@EnableAutoConfiguration
@EnableSwagger //That's all to enable swagger-springmvc out of the box
public class Application {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);
  }

}

```

#### Shutdown endpoint

```bash

curl -X POST localhost:8080/shutdown
```