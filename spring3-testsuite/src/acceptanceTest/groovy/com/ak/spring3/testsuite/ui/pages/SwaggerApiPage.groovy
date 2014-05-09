package com.ak.spring3.testsuite.ui.pages

import com.ak.spring3.testsuite.ui.modules.OauthDialogModule
import com.ak.spring3.testsuite.ui.modules.ResourceListingModule
import geb.Page

class SwaggerApiPage extends Page {
   static url = "index.html"
   static at = { title == "Swagger UI" }

   static content = {
      heading        (wait: true)       { $('div', class: 'info_title') }
      description    (wait: true)       { $('div', class: 'info_description') }
      termsOfService (wait: true)       { $('div', class: 'info_tos') }
      contact        (wait: true)       { $('div', class: 'info_contact') }
      license        (wait: true)       { $('div', class: 'info_license') }
      footer         (wait: true)       { $(".footer h4") }
      resourceListing(required: false)  { resourceName-> module ResourceListingModule , $("#resources_container  #resources #resource_${resourceName}")}
      oauthDialog(required: false) {module OauthDialogModule, $(".api-popup-dialog")}
   }

  def oauthDialog() {
    waitFor{
      $(".api-popup-dialog").displayed
    }
    oauthDialog
  }
}
