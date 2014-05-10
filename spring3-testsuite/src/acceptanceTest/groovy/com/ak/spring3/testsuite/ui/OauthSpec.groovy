package com.ak.spring3.testsuite.ui

import com.ak.spring3.testsuite.ui.modules.OperationModule
import com.ak.spring3.testsuite.ui.modules.ResourceListingModule
import com.ak.spring3.testsuite.ui.pages.PetStoreAuthPage
import com.ak.spring3.testsuite.ui.pages.SwaggerApiPage
import geb.spock.GebSpec

class OauthSpec extends GebSpec {

  def setupSpec() {
    to(SwaggerApiPage)
  }

  def "Should authenticate with petstore server"() {
    when:
      SwaggerApiPage swaggerApiPage = page
      ResourceListingModule resourceListingModule = swaggerApiPage.resourceListing('businesses')
      resourceListingModule.expand()

      OperationModule operation = resourceListingModule.operationById("businesses_list")
      operation.expand()

    and:
      operation.accessLink().click()
      swaggerApiPage.oauthDialog().scopeById('global').click()

    and:
      //Switch context to auth page
      swaggerApiPage.oauthDialog().authorizeButton.click()

      withWindow({ $('body').hasClass('oauth-page') }, page: PetStoreAuthPage) {
        page.allowButton.click()
        page.credentialsForm.username = 'ak'
        page.credentialsForm.password = 'ak'
        page.credentialsForm.find("button", id: "login").click()
      }

    then: "We are back on the swagger page with auth switched to on"
      at(SwaggerApiPage)
      page.allAuthButtons
  }
}
