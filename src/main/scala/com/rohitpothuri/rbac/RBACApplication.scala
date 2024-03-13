package com.rohitpothuri.rbac

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.{Banner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.{Bean, EnableAspectJAutoProxy}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@SpringBootApplication
@RestController
@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@RequestMapping(path = Array("/home"))
class RBACApplication {
  @GetMapping() def home = "Welcome to Analytics Engine RBAC Services"

  private final val logger = LoggerFactory.getLogger(classOf[RBACApplication])

  @Bean(destroyMethod = "shutdown")
  def taskExecutor: ThreadPoolTaskExecutor = {
    val executor = new ThreadPoolTaskExecutor
    executor.setCorePoolSize(2)
    executor.setMaxPoolSize(2)
    executor.setQueueCapacity(500)
    executor.setThreadNamePrefix("RBAC-")
    executor.initialize()
    executor
  }
}

object RBACApplication {
  def main(args: Array[String]): Unit = {
    val application = new SpringApplication(classOf[RBACApplication])
    application.setBannerMode(Banner.Mode.OFF)
    application.run()
  }
}
