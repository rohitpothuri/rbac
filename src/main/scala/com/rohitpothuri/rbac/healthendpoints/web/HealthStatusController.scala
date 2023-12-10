package com.rohitpothuri.rbac.healthendpoints.web

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.healthendpoints.model.HealthStatus
import com.rohitpothuri.rbac.healthendpoints.service.HealthStatusService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/health-status"))
class HealthStatusController(@Autowired healthStatusService: HealthStatusService) {

  @Operation(summary = "Get all HealthStatuses", description = "Returns list of HealthStatuses")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The HealthStatuses were not found")))
  @GetMapping(path = Array("/"))
  def getAllHealthStatuses(pageable: Pageable): ResponseEntity[Page[HealthStatus]] =
    ResponseEntity.ok(healthStatusService.findAll(pageable))

  @Operation(summary = "Get Health Status by ID", description = "Retrieve health status based on its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved health status"),
    new ApiResponse(responseCode = "404", description = "Health status not found")))
  @GetMapping(path = Array("/{id}"))
  def getHealthStatusesById(@PathVariable("id") id: Long): ResponseEntity[HealthStatus] = {
    val healthStatus = healthStatusService.findById(id)
    if (healthStatus.isPresent) {
      ResponseEntity.ok(healthStatus.get())
    } else {
      ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
  }

  @Operation(summary = "Save List of Health Statuses", description = "Save a list of health statuses.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved health statuses"),
    new ApiResponse(responseCode = "500", description = "Error saving health statuses")))
  @PostMapping(path = Array("/save-list"),  consumes = Array("application/json"))
  def saveHealthStatuses(@RequestBody healthStatuses: java.util.List[HealthStatus]): ResponseEntity[java.util.List[HealthStatus]] = {
    new ResponseEntity[java.util.List[HealthStatus]](healthStatusService.saveAll(healthStatuses), HttpStatus.OK)
  }

  @Operation(summary = "Save Health Status", description = "Save a single health status.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved health status"),
    new ApiResponse(responseCode = "500", description = "Error saving health status")))
  @PostMapping(path = Array("/save"), consumes = Array("application/json"))
  def saveHealthStatus(@RequestBody healthStatus: HealthStatus): ResponseEntity[HealthStatus] = {
    new ResponseEntity[HealthStatus](healthStatusService.save(healthStatus), HttpStatus.OK)
  }

  @Operation(summary = "Delete Health Status by ID", description = "Deletes a health status based on its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Health status deleted successfully"),
    new ApiResponse(responseCode = "404", description = "Health status not found"),
    new ApiResponse(responseCode = "500", description = "Error deleting health status")))
  @DeleteMapping(path = Array("/{id}"))
  def deleteHealthStatus(@PathVariable("id") id: Long): ResponseEntity[String] = {
    try {
      healthStatusService.deleteById(id)
      ResponseEntity.ok(s"Health status $id deleted successfully")
    } catch {
      case e: ServiceException =>
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s"Error deleting health status: ${e.getMessage}")
    }
  }


  @Operation(summary = "Delete All Health Statuses", description = "Deletes all health statuses.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "All health statuses deleted successfully"),
    new ApiResponse(responseCode = "500", description = "Error deleting health statuses")))
  @DeleteMapping(path = Array("/deleteAll"))
  def deleteAllHealthStatuses(): ResponseEntity[String] = {
    try {
      healthStatusService.deleteAll()
      ResponseEntity.ok("All health statuses deleted successfully")
    } catch {
      case e: ServiceException =>
        // Log the error for debugging
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s"Error deleting health statuses: ${e.getMessage}")
    }
  }

}
