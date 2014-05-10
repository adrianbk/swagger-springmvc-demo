package com.ak.spring3.testsuite.ui.pages

import geb.Page

class PetStoreAuthPage extends Page {

  static content = {
    allowButton     { $('#allow') }
    credentialsForm (required: false) { $('.oauth_submit.secondary_form') }
  }


}
