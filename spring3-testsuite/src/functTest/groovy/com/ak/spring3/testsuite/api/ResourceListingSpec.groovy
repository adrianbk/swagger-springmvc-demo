package com.ak.spring3.testsuite.api

import spock.lang.Specification

@Mixin(RestSupportMixin)
class ResourceListingSpec extends Specification {

  def "Should respond with each resource listing"() {
    when:
      def resp = rest().get(path: 'api-docs', query: [group: 'business-api'])

    then:
      //http://groovy.codehaus.org/modules/http-builder/apidocs/groovyx/net/http/HttpResponseDecorator.html
      resp.status == 200
      assertJson(resp)
      def apis = resp.data.apis

    and:
      apis.size == 4
      apis[0].description == "Businesses"
      apis[0].path == "/business-api/businesses"

      apis[1].description == "Contacts"
      apis[1].path == "/business-api/contacts"

      apis[2].description == "NoOtherMappings"
      apis[2].path == "/business-api/noothermappings"

      apis[3].description == "Test Controller"
      apis[3].path == "/business-api/test-controller"
  }

  def "code flow oauth"() {
    when:
      def resp = rest().get(path: 'api-docs', query: [group: 'business-api'])
      def auth = resp.data.authorizations

    then:
      auth.oauth2.type == 'oauth2'

      auth.oauth2.scopes.size == 1
      auth.oauth2.scopes[0].scope == 'global'
      auth.oauth2.scopes[0].description == 'access all'

      auth.oauth2.grantTypes.implicit.loginEndpoint.url == "http://petstore.swagger.wordnik.com/oauth/dialog"
      auth.oauth2.grantTypes.implicit.tokenName == "access_token"

      auth.oauth2.grantTypes.authorization_code.tokenRequestEndpoint.url == "http://petstore.swagger.wordnik.com/oauth/requestToken"
      auth.oauth2.grantTypes.authorization_code.tokenRequestEndpoint.clientIdName == "client_id"
      auth.oauth2.grantTypes.authorization_code.tokenRequestEndpoint.clientSecretName == "client_secret"

      auth.oauth2.grantTypes.authorization_code.tokenEndpoint.url == "http://petstore.swagger.wordnik.com/oauth/token"
      auth.oauth2.grantTypes.authorization_code.tokenEndpoint.tokenName == "auth_code"
  }

  def "api info"(){
    when:
      def resp = rest().get(path: 'api-docs', query: [group: 'business-api'])
      def info = resp.data.info

    then:
      info.title == "Demo Spring MVC swagger 1.2 api"
      info.description == "Sample spring mvc api based on the swagger 1.2 spec"
      info.termsOfServiceUrl == "http://en.wikipedia.org/wiki/Terms_of_service"
      info.contact == "somecontact@somewhere.com"
      info.license == "Apache 2.0"
      info.licenseUrl == "http://www.apache.org/licenses/LICENSE-2.0.html"
  }
}