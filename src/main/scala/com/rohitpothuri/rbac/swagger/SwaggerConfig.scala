package com.rohitpothuri.rbac.swagger

import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.models.info.{Contact, Info, License}
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.{ExternalDocumentation, OpenAPI, Operation}
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.{Bean, Configuration, PropertySource}
import org.springframework.core.env.Environment
import org.springframework.web.method.HandlerMethod

import java.util.Collections

@Configuration
@PropertySource(value = Array("classpath:git.properties"))
class SwaggerConfig(@Autowired buildProperties: BuildProperties,@Autowired environment: Environment) {


  @Value("${app.swagger.support.name}")
  var supportName: String = _
  @Value("${app.swagger.support.url}")
  var supportUrl: String = _
  @Value("${app.swagger.support.email}")
  var supportEmail: String = _

  @Bean def rbacOpenAPI: OpenAPI =
    new OpenAPI().info(
      new Info()
        .title("AE RBAC SWAGGER UI")
        .description(
          s"""
             |Swagger documentation for all the AE RBAC Application""".stripMargin)
        .contact(new Contact().name(supportName).url(supportUrl).email(supportEmail))
        .license(new License().name("Apache 2.0").url("https://springdoc.org/v2")))

}
