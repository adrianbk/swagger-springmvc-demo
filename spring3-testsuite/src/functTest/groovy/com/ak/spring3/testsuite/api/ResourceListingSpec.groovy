package com.ak.spring3.testsuite.api

import spock.lang.Specification

@Mixin(RestSupportMixin)
class ResourceListingSpec extends Specification {

  def "Should contain each resource listing"() {
    when:
      def resp =  rest().get(path: 'api-docs',  query : [group:'business-api'])

    then:
      //http://groovy.codehaus.org/modules/http-builder/apidocs/groovyx/net/http/HttpResponseDecorator.html
      resp.status == 200
      assertJson(resp)
      def apis = resp.data.apis

    and:
      apis.size == 4
      apis[0].description == "Businesses"
      apis[0].path == "/business-api/businesses"

      apis[1].description == "Contacts"
      apis[1].path == "/business-api/contacts"

      apis[2].description == "NoOtherMappings"
      apis[2].path == "/business-api/noothermappings"

      apis[3].description == "Test Controller"
      apis[3].path == "/business-api/test-controller"
  }


}
