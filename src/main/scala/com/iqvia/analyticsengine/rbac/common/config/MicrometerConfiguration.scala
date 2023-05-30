package com.iqvia.analyticsengine.rbac.common.config

import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MicrometerConfiguration {
  @Bean def countedAspect(registry: MeterRegistry) = new CountedAspect(registry)

  @Bean def timedAspect(registry: MeterRegistry) = new TimedAspect(registry)
}
