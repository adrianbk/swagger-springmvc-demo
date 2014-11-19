package com.ak.spring3.testsuite.api;

import spock.lang.Specification;

@Mixin(RestSupportMixin)
public class TestSpringsRestControllerSpec extends Specification {

  def "Should recognise a spring rest controller"() {
    when:
      def resp = rest().get(path: 'api-docs/business-api/test-springs-rest-controller')

    then:
      def operation = resp.data.apis.findAll { it.path == '/springsRestController' }

      operation
      operation.operations[0].method == ['GET']
      operation.operations[0].summary == ['Find a Thing']
  }
}
