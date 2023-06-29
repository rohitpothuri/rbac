package com.iqvia.analyticsengine.rbac

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@RestController
@EnableJpaRepositories()
@RequestMapping(path = Array("/"))
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
    SpringApplication.run(classOf[RBACApplication])
  }
}
