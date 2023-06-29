package com.iqvia.analyticsengine.rbac.healthendpoints.web

import com.iqvia.analyticsengine.rbac.healthendpoints.model.HealthStatus
import com.iqvia.analyticsengine.rbac.healthendpoints.service.HealthStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/healthStatusAPI"))
class HealthStatusController(@Autowired healthStatusService: HealthStatusService) {

  @GetMapping(path = Array("/healthStatuses/{id}"))
  def getHealthStatusesById(@PathVariable("id") id: Int): ResponseEntity[HealthStatus] = { // Your logic here
    // Use the `userId` in your method implementation
    val z = healthStatusService.save(new HealthStatus("test","test"))
    z
    val k = healthStatusService.findById(id)
    new ResponseEntity[HealthStatus](healthStatusService.findById(id), HttpStatus.OK)
  }

  @PostMapping(path = Array("/healthStatuses"),  consumes = Array("application/json"))
  def saveHealthStatuses(@RequestBody healthStatuses: java.util.List[HealthStatus]): ResponseEntity[java.util.List[HealthStatus]] = {
    new ResponseEntity[java.util.List[HealthStatus]](healthStatusService.saveAll(healthStatuses), HttpStatus.OK)
  }

  @PostMapping(path = Array("/healthStatus"), consumes = Array("application/json"))
  def saveHealthStatus(@RequestBody healthStatus: HealthStatus): ResponseEntity[HealthStatus] = {
    new ResponseEntity[HealthStatus](healthStatusService.save(healthStatus), HttpStatus.OK)
  }

}
