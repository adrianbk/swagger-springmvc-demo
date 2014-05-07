swagger-springmvc-demo
======================

A set of apps that test or demo swagger-spring mvc https://github.com/martypitt/swagger-springmvc/


#### Building
```
./gradlew build
```

- If using intellij ues file >> open and point to the build.gradle file
- As of May 4 2014 there is a bug with intellij and gradle 1.12 - the intellij EAP has a fix so use the EAP version




#### Swagger UI Oauth Support

Update swagger-oauth.js as appropriate to your app

```javascript
var redirectUrl = host.protocol + '//' + host.host + "/spring3-accounting/o2c.html";
```