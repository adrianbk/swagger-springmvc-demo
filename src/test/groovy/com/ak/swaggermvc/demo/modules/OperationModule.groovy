package com.ak.swaggermvc.demo.modules

import geb.Module

class OperationModule extends Module {

  static content = {
    httpMethod { $('.heading > h3 .http_method').text() }
    path { $('.heading > h3 .path').text() }
    summary { $('.heading .options >li a').text() }
    expandLink { $('.heading .options >li a') }
    responseModel { module ModelSchemaModule, $('.content p span.model-signature') }
    }

  def expand() {
    expandLink.click()
  }

  def implementationNotes() {
    waitFor {
      $('.content >p').text()
    }
  }

}
