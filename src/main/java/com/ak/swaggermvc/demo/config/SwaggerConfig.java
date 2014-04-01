package com.ak.swaggermvc.demo.config;

import com.mangofactory.swagger.authorization.AuthorizationContext;
import com.mangofactory.swagger.configuration.JacksonScalaSupport;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.configuration.SpringSwaggerModelConfig;
import com.mangofactory.swagger.configuration.SwaggerGlobalSettings;
import com.mangofactory.swagger.core.DefaultSwaggerPathProvider;
import com.mangofactory.swagger.core.SwaggerApiResourceListing;
import com.mangofactory.swagger.core.SwaggerPathProvider;
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.model.Authorization;
import com.wordnik.swagger.model.AuthorizationCodeGrant;
import com.wordnik.swagger.model.AuthorizationScope;
import com.wordnik.swagger.model.AuthorizationType;
import com.wordnik.swagger.model.GrantType;
import com.wordnik.swagger.model.ImplicitGrant;
import com.wordnik.swagger.model.LoginEndpoint;
import com.wordnik.swagger.model.OAuth;
import com.wordnik.swagger.model.OAuthBuilder;
import com.wordnik.swagger.model.TokenEndpoint;
import com.wordnik.swagger.model.TokenRequestEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"com.mangofactory.swagger.controllers", "com.mangofactory.swagger.configuration"})
public class SwaggerConfig {

  public static final List<String> DEFAULT_INCLUDE_PATTERNS = Arrays.asList(new String[]{
      "/business.*",
      "/some.*",
      "/contacts.*"
  });
  public static final String SWAGGER_GROUP = "business-api";
  public static final String RELATIVE_GROUP = "relative-group";

  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;
  @Autowired
  private SpringSwaggerModelConfig springSwaggerModelConfig;

  /**
   * Adds the jackson scala module to the MappingJackson2HttpMessageConverter registered with spring
   * Swagger core models are scala so we need to be able to convert to JSON
   * Also registers some custom serializers needed to transform swagger models to swagger-ui required json format
   */
  @Bean
  public JacksonScalaSupport jacksonScalaSupport() {
    JacksonScalaSupport jacksonScalaSupport = new JacksonScalaSupport();
    //Set to false to disable
    jacksonScalaSupport.setRegisterScalaModule(true);
    return jacksonScalaSupport;
  }


  /**
   * Global swagger settings
   */
  @Bean
  public SwaggerGlobalSettings swaggerGlobalSettings() {
    SwaggerGlobalSettings swaggerGlobalSettings = new SwaggerGlobalSettings();
    swaggerGlobalSettings.setGlobalResponseMessages(springSwaggerConfig.defaultResponseMessages());
    swaggerGlobalSettings.setIgnorableParameterTypes(springSwaggerConfig.defaultIgnorableParameterTypes());
    swaggerGlobalSettings.setParameterDataTypes(springSwaggerModelConfig.defaultParameterDataTypes());
    return swaggerGlobalSettings;
  }

  /**
   * API Info as it appears on the swagger-ui page
   */
  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "Demo Spring MVC swagger API 1.2",
        "Sample spring mvc api based on the swagger 1.2 spec",
        "http://en.wikipedia.org/wiki/Terms_of_service",
        "somecontact@somewhere.com",
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0.html"
    );
    return apiInfo;
  }

  /**
   * Configure a SwaggerApiResourceListing for each swagger instance within your app. e.g. 1. private  2. external apis
   * Required to be a spring bean as spring will call the postConstruct method to bootstrap swagger scanning.
   *
   * @return
   */
  @Bean
  public SwaggerApiResourceListing swaggerApiResourceListing() {
    //The group name is important and should match the group set on ApiListingReferenceScanner
    //Note that swaggerCache() is by DefaultSwaggerController to serve the swagger json
    SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing(springSwaggerConfig.swaggerCache(), SWAGGER_GROUP);

    //Set the required swagger settings
    swaggerApiResourceListing.setSwaggerGlobalSettings(swaggerGlobalSettings());

    //Use a custom path provider or springSwaggerConfig.defaultSwaggerPathProvider()
    swaggerApiResourceListing.setSwaggerPathProvider(demoPathProvider());

    //Supply the API Info as it should appear on swagger-ui web page
    swaggerApiResourceListing.setApiInfo(apiInfo());

    //Global authorization - see the swagger documentation
    swaggerApiResourceListing.setAuthorizationTypes(authorizationTypes());

    //Sets up an auth context - i.e. which controller request paths to apply global auth to
    swaggerApiResourceListing.setAuthorizationContext(authorizationContext());

    //Every SwaggerApiResourceListing needs an ApiListingReferenceScanner to scan the spring request mappings
    swaggerApiResourceListing.setApiListingReferenceScanner(apiListingReferenceScanner());
    return swaggerApiResourceListing;
  }

  @Bean
  /**
   * The ApiListingReferenceScanner does most of the work.
   * Scans the appropriate spring RequestMappingHandlerMappings
   * Applies the correct absolute paths to the generated swagger resources
   */
  public ApiListingReferenceScanner apiListingReferenceScanner() {
    ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();

    //Picks up all of the registered spring RequestMappingHandlerMappings for scanning
    apiListingReferenceScanner.setRequestMappingHandlerMapping(springSwaggerConfig.swaggerRequestMappingHandlerMappings());

    //Excludes any controllers with the supplied annotations
    apiListingReferenceScanner.setExcludeAnnotations(springSwaggerConfig.defaultExcludeAnnotations());

    //
    apiListingReferenceScanner.setResourceGroupingStrategy(springSwaggerConfig.defaultResourceGroupingStrategy());

    //Path provider used to generate the appropriate uri's
    apiListingReferenceScanner.setSwaggerPathProvider(demoPathProvider());

    //Must match the swagger group set on the SwaggerApiResourceListing
    apiListingReferenceScanner.setSwaggerGroup(SWAGGER_GROUP);

    //Only include paths that match the supplied regular expressions
    apiListingReferenceScanner.setIncludePatterns(DEFAULT_INCLUDE_PATTERNS);

    return apiListingReferenceScanner;
  }

  /**
   * Example of a custom path provider
   */
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


    List<GrantType> grantTypes = newArrayList();

    LoginEndpoint loginEndpoint = new LoginEndpoint("http://petstore.swagger.wordnik.com/oauth/dialog");
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
    AuthorizationContext authorizationContext =
        new AuthorizationContext.AuthorizationContextBuilder(authorizations)
            .withIncludePatterns(DEFAULT_INCLUDE_PATTERNS)
            .build();
    return authorizationContext;
  }

  //Relative path example
  @Bean
  public SwaggerApiResourceListing relativeSwaggerApiResourceListing() {
    SwaggerApiResourceListing swaggerApiResourceListing = new SwaggerApiResourceListing(springSwaggerConfig.swaggerCache(), RELATIVE_GROUP);
    swaggerApiResourceListing.setSwaggerGlobalSettings(swaggerGlobalSettings());
    swaggerApiResourceListing.setSwaggerPathProvider(relativeSwaggerPathProvider());
    swaggerApiResourceListing.setApiListingReferenceScanner(relativeApiListingReferenceScanner());
    return swaggerApiResourceListing;
  }

  @Bean
  public ApiListingReferenceScanner relativeApiListingReferenceScanner() {
    ApiListingReferenceScanner apiListingReferenceScanner = new ApiListingReferenceScanner();
    apiListingReferenceScanner.setRequestMappingHandlerMapping(springSwaggerConfig.swaggerRequestMappingHandlerMappings());
    apiListingReferenceScanner.setExcludeAnnotations(springSwaggerConfig.defaultExcludeAnnotations());
    apiListingReferenceScanner.setResourceGroupingStrategy(springSwaggerConfig.defaultResourceGroupingStrategy());
    apiListingReferenceScanner.setSwaggerPathProvider(relativeSwaggerPathProvider());
    apiListingReferenceScanner.setSwaggerGroup(RELATIVE_GROUP);
    apiListingReferenceScanner.setIncludePatterns(DEFAULT_INCLUDE_PATTERNS);
    return apiListingReferenceScanner;
  }

  @Bean
  public SwaggerPathProvider relativeSwaggerPathProvider() {
    return new DemoRelativeSwaggerPathProvider();
  }

  private class DemoRelativeSwaggerPathProvider extends DefaultSwaggerPathProvider{
    @Override public String getAppBasePath() {
      return "/swagger-springmvc-demo";
    }

    @Override public String getSwaggerDocumentationBasePath() {
      return "/api-docs";
    }
  }


}
