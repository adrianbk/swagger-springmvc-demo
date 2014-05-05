package com.ak.spring3.testsuite.api

import groovyx.net.http.RESTClient
import org.springframework.http.MediaType

class RestSupportMixin {

    def rest = {
        new RESTClient( 'http://localhost:9080/spring3-testsuite/' )
    }

    def assertJson = {
      assert it.contentType == MediaType.APPLICATION_JSON_VALUE
      true
    }

}
