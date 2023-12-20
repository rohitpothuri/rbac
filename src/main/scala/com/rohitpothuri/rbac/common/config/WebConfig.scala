package com.rohitpothuri.rbac.common.config

import com.rohitpothuri.rbac.realm.service.RealmInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurer}

@Configuration
class WebConfig(@Autowired realmInterceptor: RealmInterceptor) extends WebMvcConfigurer{
  override def addInterceptors(registry: InterceptorRegistry): Unit = {
    registry.addInterceptor(realmInterceptor)
  }

  @throws[Exception]
  protected def configure(http: HttpSecurity): Unit = {
    http.csrf.disable
  }

}
