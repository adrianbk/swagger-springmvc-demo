package com.ak.spring3.testsuite.ui.modules

import geb.Module

class ResourceListingModule extends Module {
  static content = {
    name          (wait: true) { $('.heading > h2').text() }
    expandLink    (wait: true) { $('.heading > h2 a') }
    operations    (wait: true) { moduleList OperationModule, $('.endpoints .endpoint .operations') }
    operationById (wait: true) { id -> module OperationModule, $(".endpoints .endpoint .operations #$id") }
  }

  def expand() {
    expandLink.click()
  }
}
