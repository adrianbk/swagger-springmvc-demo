package com.ak.swaggermvc.demo.config;

import com.mangofactory.swagger.authorization.AuthorizationContext;
import com.mangofactory.swagger.configuration.JacksonScalaSupport;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.configuration.SpringSwaggerModelConfig;
import com.mangofactory.swagger.configuration.SwaggerGlobalSettings;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.model.Authorization;
import com.wordnik.swagger.model.AuthorizationScope;
import com.wordnik.swagger.model.AuthorizationType;
import com.wordnik.swagger.model.GrantType;
import com.wordnik.swagger.model.ImplicitGrant;
import com.wordnik.swagger.model.LoginEndpoint;
import com.wordnik.swagger.model.OAuth;
import com.wordnik.swagger.model.OAuthBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
public class SwaggerConfig {

  public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList(new String[]{
      "/business.*",
      "/some.*",
      "/contacts.*"
  });
  public static final String SWAGGER_GROUP = "business-api";

  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;
  @Autowired
  private SpringSwaggerModelConfig springSwaggerModelConfig;

  /**
   * Adds the jackson scala module to the MappingJackson2HttpMessageConverter registered with spring
   * Swagger core models are scala so we need to be able to convert to JSON
   * Also registers som custom serializers needed to transform swagger models to swagger-ui required json format
   */
  @Bean
  public JacksonScalaSupport jacksonScalaSupport() {
    JacksonScalaSupport jacksonScalaSupport = new JacksonScalaSupport();
    //Set to false to disable
    jacksonScalaSupport.setRegisterScalaModule(true);
    return jacksonScalaSupport;
  }

  @Bean
  public SwaggerApiResourceListing swaggerApiResourceListing() {
    SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing(springSwaggerConfig.swaggerCache(), SWAGGER_GROUP);
    swaggerApiResourceListing.setSwaggerPathProvider(demoPathProvider());
    swaggerApiResourceListing.setApiInfo(apiInfo());
    swaggerApiResourceListing.setAuthorizationTypes(authorizationTypes());
    swaggerApiResourceListing.setSwaggerGlobalSettings(swaggerGlobalSettings());
    swaggerApiResourceListing.setAuthorizationContext(authorizationContext());

    ApiListingReferenceScanner apiListingReferenceScanner = apiListingReferenceScanner();
    swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner);
    return swaggerApiResourceListing;
  }

  @Bean
  public SwaggerGlobalSettings swaggerGlobalSettings() {
    SwaggerGlobalSettings swaggerGlobalSettings = new SwaggerGlobalSettings();
    swaggerGlobalSettings.setGlobalResponseMessages(springSwaggerConfig.defaultResponseMessages());
    swaggerGlobalSettings.setIgnorableParameterTypes(springSwaggerConfig.defaultIgnorableParameterTypes());
    swaggerGlobalSettings.setParameterDataTypes(springSwaggerModelConfig.defaultParameterDataTypes());
    return swaggerGlobalSettings;
  }

  @Bean
  public ApiListingReferenceScanner apiListingReferenceScanner() {
    ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
    apiListingReferenceScanner.setRequestMappingHandlerMapping(springSwaggerConfig.swaggerRequestMappingHandlerMappings());
    apiListingReferenceScanner.setExcludeAnnotations(springSwaggerConfig.defaultExcludeAnnotations());
    apiListingReferenceScanner.setResourceGroupingStrategy(springSwaggerConfig.defaultResourceGroupingStrategy());
    apiListingReferenceScanner.setSwaggerPathProvider(demoPathProvider());
    //Must match the swagger group set on the SwaggerApiResourceListing
    apiListingReferenceScanner.setSwaggerGroup("business-api");
    //Only add the businesses endpoints to this api listing
    apiListingReferenceScanner.setIncludePatterns(DEFAULT_INCLUDE_PATTERNS);
    return apiListingReferenceScanner;
  }

  @Bean
  public DemoPathProvider demoPathProvider() {
    DemoPathProvider demoPathProvider = new DemoPathProvider();
    demoPathProvider.setDefaultSwaggerPathProvider(springSwaggerConfig.defaultSwaggerPathProvider());
    return demoPathProvider;
  }

  private List<AuthorizationType> authorizationTypes() {
    ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

    List<AuthorizationScope> authorizationScopeList = newArrayList();
    authorizationScopeList.add(new AuthorizationScope("global", "access all"));

    LoginEndpoint loginEndpoint = new LoginEndpoint("https://logmein.com");

    List<GrantType> grantTypes = newArrayList();
    grantTypes.add(new ImplicitGrant(loginEndpoint, "AccessToken"));

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

    AuthorizationContext authorizationContext =
        new AuthorizationContext.AuthorizationContextBuilder(authorizations)
            .withIncludePatterns(DEFAULT_INCLUDE_PATTERNS)
            .build();


    return authorizationContext;
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "Demo Spring MVC swagger 1.2 api",
        "Sample spring mvc api based on the swagger 1.2 spec",
        "http://en.wikipedia.org/wiki/Terms_of_service",
        "somecontact@somewhere.com",
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0.html"
    );
    return apiInfo;
  }
}
