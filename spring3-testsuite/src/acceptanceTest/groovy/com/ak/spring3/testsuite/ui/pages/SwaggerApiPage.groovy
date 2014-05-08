package com.ak.spring3.testsuite.ui.pages

import com.ak.spring3.testsuite.ui.modules.ResourceListingModule
import geb.Page

class SwaggerApiPage extends Page {
   static url = "index.html"
   static at = { title == "Swagger UI" }

   static content = {
      heading        { $('div', class: 'info_title') }
      description    { $('div', class: 'info_description') }
      termsOfService { $('div', class: 'info_tos') }
      contact        { $('div', class: 'info_contact') }
      license        { $('div', class: 'info_license') }
      footer         { $(".footer h4") }
      resourceListing(wait: true) { resourceName-> module ResourceListingModule , $("#resources_container  #resources #resource_${resourceName}")}
   }

}
