package com.ak.swaggermvc.demo.modules

import geb.Module

class ResourceListingModule extends Module{
   static content = {
      name     { $('.heading > h2').text() }
   }

}
