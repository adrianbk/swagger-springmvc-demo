package com.ak.swaggermvc.demo.pages

import com.ak.swaggermvc.demo.modules.ResourceListingModule
import geb.Page

class SwaggerApiPage extends Page {
   static url = ""
   static at = { title == "Swagger UI" }

   static content = {
      heading        { $('div', class: 'info_title') }
      description    { $('div', class: 'info_description') }
      termsOfService { $('div', class: 'info_tos') }
      contact        { $('div', class: 'info_contact') }
      license        { $('div', class: 'info_license') }
      footer         { $(".footer h4") }
      resourceListing { index -> module ResourceListingModule , $('#resources_container #resources .resource', index) }

   }

}
