package com.ak.swaggermvc.demo

import com.ak.swaggermvc.demo.pages.SwaggerApiPage
import geb.spock.GebSpec;

public class SwaggerBusinessApiPageSpec extends GebSpec {

   def "Swagger UI Static Content"() {
    when:
      to SwaggerApiPage
    then:
      title == "Swagger UI"
      page.heading.text() == "Demo Spring MVC swagger 1.2 api"
      page.description .text() == "Sample spring mvc api based on the swagger 1.2 spec"
      page.termsOfService.text() == "Terms of service"
      page.contact.text() == "Contact the developer"
      page.license.text() == "Apache 2.0"
      page.footer.text().replaceAll(' ', '') == "[baseurl:http://127.0.0.1:8080/swagger-springmvc-demo/api-docs/business-api,apiversion:1]"
   }


}