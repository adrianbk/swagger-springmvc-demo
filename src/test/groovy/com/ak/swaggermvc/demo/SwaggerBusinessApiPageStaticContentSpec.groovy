package com.ak.swaggermvc.demo

import com.ak.swaggermvc.demo.pages.SwaggerApiPage
import geb.spock.GebSpec;

public class SwaggerBusinessApiPageStaticContentSpec extends GebSpec {

   def "Swagger UI Static Content"() {
    when:
      SwaggerApiPage.url = "${baseUrl()}/swaggerBusiness"
      to(SwaggerApiPage)

    then: "Title"
      title == "Swagger UI"
    and: "API Meta data"

      page.heading.text() == "Demo Spring MVC swagger 1.2 api"
      page.description.text() == "Sample spring mvc api based on the swagger 1.2 spec"
      page.termsOfService.text() == "Terms of service"
      page.contact.text() == "Contact the developer"
      page.license.text() == "Apache 2.0"

    and: "footer"
      page.footer.text().replaceAll(' ', '') == "[baseurl:${baseUrl()}/api-docs/business-controller,apiversion:1]"

    and: "contains business resource listing"
      page.resourceListing('Businesses').name == 'business-controller : Businesses'
   }

   private String baseUrl() {
      return browser.config.rawConfig.baseUrl
   }
}