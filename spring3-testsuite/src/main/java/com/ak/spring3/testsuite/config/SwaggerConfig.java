package com.ak.spring3.testsuite.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Authorization;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.AuthorizationType;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.AuthorizationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.builders.PathSelectors.*;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket swaggerSpringMvcPlugin() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("business-api")
            .select()
              .paths(paths())
              .build()
            .apiInfo(apiInfo())
            .authorizationTypes(authorizationTypes())
            .authorizationContext(authorizationContext());
  }

  private Predicate<String> paths() {
    return or(
        regex("/business.*"),
        regex("/some.*"),
        regex("/contacts.*"),
        regex("/pet.*"),
        regex("/springsRestController.*"),
        regex("/test.*"));
  }

  /**
   * API Info as it appears on the swagger-ui page
   */
  private ApiInfo apiInfo() {
    return new ApiInfo(
            "Demo Spring MVC swagger 1.2 api",
            "Sample spring mvc api based on the swagger 1.2 spec",
            "1.0",
            "http://en.wikipedia.org/wiki/Terms_of_service",
            "somecontact@somewhere.com",
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0.html"
    );
  }


  private List<AuthorizationType> authorizationTypes() {
    ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

    List<AuthorizationScope> authorizationScopeList = newArrayList();
    authorizationScopeList.add(new AuthorizationScope("global", "access all"));

    List<GrantType> grantTypes = newArrayList();

    LoginEndpoint loginEndpoint = new LoginEndpoint("http://petstore.swagger.wordnik.com/api/oauth/dialog");
    grantTypes.add(new ImplicitGrant(loginEndpoint, "access_token"));

    TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("http://petstore.swagger.wordnik.com/oauth/requestToken", "client_id", "client_secret");
    TokenEndpoint tokenEndpoint = new TokenEndpoint("http://petstore.swagger.wordnik.com/oauth/token", "auth_code");

    AuthorizationCodeGrant authorizationCodeGrant = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
    grantTypes.add(authorizationCodeGrant);

    OAuth oAuth = new OAuthBuilder()
            .scopes(authorizationScopeList)
            .grantTypes(grantTypes)
            .build();

    authorizationTypes.add(oAuth);
    return authorizationTypes;
  }

  @Bean
  public AuthorizationContext authorizationContext() {
    List<Authorization> authorizations = newArrayList();

    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
    authorizations.add(new Authorization("oauth2", authorizationScopes));
    return new AuthorizationContext.AuthorizationContextBuilder()
        .withAuthorizations(authorizations)
        .build();
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(500000);
    return multipartResolver;
  }
}
