package com.ak.spring3.testsuite.ui

import com.ak.spring3.testsuite.ui.modules.OperationModule
import com.ak.spring3.testsuite.ui.modules.ResourceListingModule
import com.ak.spring3.testsuite.ui.pages.SwaggerApiPage
import geb.spock.GebSpec
import spock.lang.Ignore

class SwaggerBusinessApiModelSpec extends GebSpec {

//  def setupSpec() {
//    SwaggerApiPage.url = "${browser.config.rawConfig.baseUrl}/swaggerBusiness"
//    to(SwaggerApiPage)
//  }


  @Ignore
  def "get list of businesses model"() {
  when:
    SwaggerApiPage swaggerApiPage = page
    ResourceListingModule resourceListingModule = swaggerApiPage.resourceListing('business-controller')
    resourceListingModule.expand()

  then:
    resourceListingModule.operations.size() == 7
    OperationModule operation = resourceListingModule.operationById("business-controller_list")
    operation.path == '/businesses'
    operation.summary == 'get a list of businesses'
    operation.httpMethod == 'GET'

  when:
    operation.expand()

  then:
    operation.implementationNotes() == 'Returns all businesses in the cache'
    operation.responseModel.modelLink.text() == 'Model'
    operation.responseModel.description().text().startsWith('Business {')

  }
}
