package com.ak.spring3.testsuite.api
import spock.lang.Specification

@Mixin(RestSupportMixin)
class ResourceListingSpec extends Specification {

  def "Should contain each resource listing"() {
    when:
      def resp =  rest().get(path: 'api-docs',  query : [group:'business-api'])

    then:
      resp.status == 200
      assertJson(resp)

    and:
      def apis = resp.data.apis
      apis[0].description == "Businesses"
      apis[0].path == "/business-api/businesses"
  }
}
