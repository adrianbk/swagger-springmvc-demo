package com.ak.swaggermvc.demo.modules

import geb.Module

class OperationModule extends Module {

    static content = {
        httpMethod { $('.heading > h3 .http_method').text() }
//      executeButton {$('.content')}
    }
}
