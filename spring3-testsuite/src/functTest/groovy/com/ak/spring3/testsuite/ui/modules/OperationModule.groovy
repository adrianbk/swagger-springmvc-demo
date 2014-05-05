package com.ak.spring3.testsuite.ui.modules

import geb.Module

class OperationModule extends Module {

  static content = {
    httpMethod { $('.heading > h3 > span.http_method > a').text() }
    path { $('.heading > h3 > span.path > a').text() }
    summary { $('.heading .options >li a').text() }
    expandLink { $('.heading > h3 > span.path > a') }
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
