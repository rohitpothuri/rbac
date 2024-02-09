package com.rohitpothuri.rbac.swagger

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.{Bean, Configuration, PropertySource}
import org.springframework.core.env.Environment
import org.springframework.web.method.HandlerMethod
import springfox.documentation.builders.{ParameterBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.schema.ModelRef
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import java.util.Collections

@Configuration
@PropertySource(value = Array("classpath:git.properties"))
@EnableSwagger2
class SwaggerConfig(@Autowired buildProperties: BuildProperties,@Autowired environment: Environment) {


  @Value("${app.swagger.support.name}")
  var supportName: String = _
  @Value("${app.swagger.support.url}")
  var supportUrl: String = _
  @Value("${app.swagger.support.email}")
  var supportEmail: String = _

  @Bean def api: Docket = {
    val api = new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build()
    api
  }
}
