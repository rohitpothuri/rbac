package com.rohitpothuri.rbac.common.config

import com.rohitpothuri.rbac.company.model.Company
import com.rohitpothuri.rbac.department.model.Department
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
class RestConfiguration extends RepositoryRestConfigurer {

  override def configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, registry: CorsRegistry): Unit = {
    config.exposeIdsFor(classOf[Department])
    config.exposeIdsFor(classOf[Company])
  }
}


