package com.ak.spring3.testsuite.ui.modules

import geb.Module


class ModelSchemaModule extends Module {

  static content = {
    modelLink { $('.model-signature .signature-nav a.description-link') }
    schemaLink { $('.model-signature .signature-nav a.snippet-link') }

  }

  def description() {
    $('.model-signature .signature-container .description')
  }
}
