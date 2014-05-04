######## swagger-static

Downloads the swagger-ui code from github and adds its dist directory into the manifest of a jar file.
Effectively a webjar which can be used by the other demo projects.

The swagger ui version is controlled by the build.gradle file where `swaggerUiVersion` is the git tag of the
https://github.com/wordnik/swagger-ui repo which is used to package the jar.

```
ext {
      swaggerUiVersion = '2.0.16'
...


```