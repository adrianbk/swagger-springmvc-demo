package com.ak.spring3.testsuite.api

import groovyx.net.http.RESTClient
import org.springframework.http.MediaType

class RestSupportMixin {

  def rest = { String base = 'http://localhost:9080/spring3-testsuite/' ->
    new RESTClient(base)
  }

  def assertJson = {
    assert it.contentType == MediaType.APPLICATION_JSON_VALUE
    true
  }

}
