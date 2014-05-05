swagger-springmvc-demo
======================

A sample app using https://github.com/martypitt/swagger-springmvc/

- Running the app:

```./gradlew :spring3-accounting:tomcatRun -i```

- Oauth Support

Update swagger-oauth.js as appropriate to your app

```javascript
var redirectUrl = host.protocol + '//' + host.host + "/spring3-accounting/o2c.html";
```

 As of May 4 2014 there is a bug with intellij and gradle 1.12 - the intellij EAP has a fix so use the EAP version


## Versions

This repo is tagged in the following format in an attempt to keep track of changing swagger-springmvc and swagger-ui versions
-- swagger-springmvc--<swagger-springmvc version>-swagger-ui--<swagger-ui version or branch>
-- e.g. swagger-springmvc--0.8.2-SNAPSHOT-swagger-ui--oauth

## Swagger UI Business Example
http://localhost:9080/spring3-accounting/swaggerBusiness

## Relative URL example (Relative URLS not recommended)
http://localhost:9080/spring3-accounting/swaggerRelative
