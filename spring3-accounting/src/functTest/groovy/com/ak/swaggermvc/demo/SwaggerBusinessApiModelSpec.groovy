package com.ak.swaggermvc.demo

import com.ak.swaggermvc.demo.modules.OperationModule
import com.ak.swaggermvc.demo.modules.ResourceListingModule
import com.ak.swaggermvc.demo.pages.SwaggerApiPage
import geb.spock.GebSpec

class SwaggerBusinessApiModelSpec extends GebSpec {

  def setupSpec() {
    SwaggerApiPage.url = "${browser.config.rawConfig.baseUrl}/swaggerBusiness"
    to(SwaggerApiPage)
  }

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
