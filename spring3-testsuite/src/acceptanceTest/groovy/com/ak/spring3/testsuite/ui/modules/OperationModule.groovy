package com.ak.spring3.testsuite.ui.modules

import geb.Module

class OperationModule extends Module {

  static content = {
    httpMethod            (wait: true) { $('.heading > h3 > span.http_method > a').text() }
    path                  (wait: true) { $('.heading > h3 > span.path > a').text() }
    summary               (wait: true) { $('.heading .options >li a').text() }
    expandLink            (wait: true) { $('.heading > h3 > span.path > a') }
    responseModel         (wait: true) { module ModelSchemaModule, $('.content p span.model-signature') }
    implementationNotes   (wait: true) { $('.content >p').text() }
    _accessLink(required: false) {$('.content .access span')}
  }

  def expand() {
    waitFor { expandLink.displayed }
    expandLink.click()
  }

  def accessLink() {
    waitFor { _accessLink.displayed }
    _accessLink
  }
}
