package com.ak.spring3.testsuite.api

import spock.lang.Specification

@Mixin(RestSupportMixin)
class FileUploadSpec extends Specification {


  def "Should  have file upload attributes"() {
    expect:
      def resp = rest().get(path: 'api-docs/business-api/test-controller')

      resp.data.consumes.contains "multipart/form-data"

      def operation = resp.data.apis.findAll { it.path == '/test/upload' }
      operation.operations[0].method == ['POST']
      operation.operations[0].consumes[0] == ["multipart/form-data"]

      operation.operations[0].parameters[0][1].name == 'file'
      operation.operations[0].parameters[0][1].type == 'File'
      operation.operations[0].parameters[0][1].paramType == 'form'
  }
}
