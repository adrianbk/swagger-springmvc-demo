#swagger-multiplugin

An example spring boot appication (without swagger ui) of how swagger groups are applied.
Typically an application using more than one swagger group will have either:

- An accompanying swagger-ui page for each swagger group, with the base swagger url configured for that group.
- _OR_ A single swagger ui page with a dropdown for swagger-ui's `baseUrl` html input element
- _OR_ Some other dynamic way of serving the swagger-ui html markup with a dynamic `baseUrl`


```javascript
$(function () {
      window.swaggerUi = new SwaggerUi({
      url: window.location.origin +"/myapp/api-docs?group=group1",
      dom_id: "swagger-ui-container",
```


## Run it

```bash
./gradlew :swagger-multiplugin:bootRun
```

### group1 Resource Listing
http://localhost:8080/api-docs?group=group1

#### group1 Api Declaration 
http://localhost:8080/api-docs/group1/albums

### group2 Resource Listing
http://localhost:8080/api-docs?group=group2

#### group2 Api Declaration
http://localhost:8080/api-docs/group2/artists