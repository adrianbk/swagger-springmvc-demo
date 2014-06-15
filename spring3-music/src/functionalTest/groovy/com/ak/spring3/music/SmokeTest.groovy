package com.ak.spring3.music

import groovyx.net.http.RESTClient
import spock.lang.Specification;

class SmokeTest extends Specification {


  def "Should get an api resource listing and sdoc.jsp"() {
    expect:
      rest().get(path: 'api-docs').data.swaggerVersion == "1.2"

    and:
      rest().get(path: 'sdoc.jsp').status == 200

  }

  def rest = {
    new RESTClient('http://localhost:9080/spring3-music/')
  }
}