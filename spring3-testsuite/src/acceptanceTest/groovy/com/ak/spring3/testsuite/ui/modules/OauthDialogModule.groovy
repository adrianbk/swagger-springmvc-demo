package com.ak.spring3.testsuite.ui.modules;

import geb.Module;

public class OauthDialogModule extends Module {
  static content = {
    scopes          { id -> $('.api-popup-scopes input') }
    scopeById       { id -> $(".api-popup-scopes input[scope=$id]") }
    authorizeButton { $('.api-popup-actions .api-popup-authbtn') }
  }
}
