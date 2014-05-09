package com.ak.spring3.testsuite.ui

import com.ak.spring3.testsuite.ui.modules.OperationModule
import com.ak.spring3.testsuite.ui.modules.ResourceListingModule
import com.ak.spring3.testsuite.ui.pages.SwaggerApiPage
import geb.spock.GebSpec

class SwaggerBusinessApiModelSpec extends GebSpec {

  def setupSpec() {
    to(SwaggerApiPage)
  }

  def "get list of businesses model"() {
  when:
    SwaggerApiPage swaggerApiPage = page
    ResourceListingModule resourceListingModule = swaggerApiPage.resourceListing('businesses')
    resourceListingModule.expand()

  then:
    resourceListingModule.operations.size() == 10
    OperationModule operation = resourceListingModule.operationById("businesses_list")
    operation.httpMethod == 'GET'
    operation.summary == 'get a list of businesses'
    operation.path == '/businesses'

  when:
    operation.expand()

  then:
    operation.implementationNotes == 'Returns all businesses in the cache'
    operation.responseModel.modelLink.text() == 'Model'
    operation.responseModel.description().text().startsWith('Business {')

  }
}
