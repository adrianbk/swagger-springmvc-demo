package com.ak.swaggermvc.demo.modules

import geb.Module

class ResourceListingModule extends Module {
    static content = {
        name { $('.heading > h2').text() }
        expandLink { $('.heading > h2 a')}
        operations { moduleList OperationModule, $('.endpoints .endpoint .operations') }
        operationById {id ->  module OperationModule, $(".endpoints .endpoint .operations > #$id") }
    }

    def expand(){
        expandLink.click()
    }
}
