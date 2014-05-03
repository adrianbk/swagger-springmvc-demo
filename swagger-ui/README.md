######## swagger-static

Downloads the swagger-ui code from github and adds its dist directory into the manifest of a jar file.
Effectively a webjar which can be usd by the other demo projects

The swagger ui version is controled by the build.gradle file

```
ext {
      swaggerUiVersion = '2.0.16'
...


```