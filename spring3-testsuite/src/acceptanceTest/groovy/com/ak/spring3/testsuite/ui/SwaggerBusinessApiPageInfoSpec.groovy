package com.ak.spring3.testsuite.ui

import com.ak.spring3.testsuite.ui.pages.SwaggerApiPage
import geb.spock.GebSpec
import spock.lang.Ignore;

public class SwaggerBusinessApiPageInfoSpec extends GebSpec {

  def "Swagger UI api info"() {
    when:
      to(SwaggerApiPage)

    then: "Title"
      title == "Swagger UI"

    and: "API Meta data"
      page.heading.text()         == "Demo Spring MVC swagger 1.2 api"
      page.description.text()     == "Sample spring mvc api based on the swagger 1.2 spec"
      page.termsOfService.text()  == "Terms of service"
      page.contact.text()         == "Contact the developer"
      page.license.text()         == "Apache 2.0"

    and: "footer"
      page.footer.text().replaceAll(' ', '') == "[baseurl:${baseUrl()}api-docs,apiversion:1]"

  }

  private String baseUrl() {
    return browser.config.rawConfig.baseUrl
  }
}