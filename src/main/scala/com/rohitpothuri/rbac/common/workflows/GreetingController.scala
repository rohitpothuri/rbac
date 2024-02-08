package com.rohitpothuri.rbac.common.workflows

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.service.DepartmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.{ConstraintViolation, ConstraintViolationException, Valid, ValidationException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, HttpStatusCode, ResponseEntity}
import org.springframework.web.bind.annotation.*
import com.rohitpothuri.rbac.realm.service.IncludeRealmInterceptor

import java.util.Optional
import scala.jdk.CollectionConverters.*
@RestController
@RequestMapping(path = Array("/greeting"))
@IncludeRealmInterceptor
@Tag(name = "Greetings", description = "Endpoints for managing departments")
class GreetingController(@Autowired workflowClient: WorkflowClient) {
  @Operation(summary = "Get all departments", description = "Returns list of departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The departments were not found")))
  @PutMapping(path = Array("/"))
  def invokeGreeting = {
    ResponseEntity.ok()
  }
}
  

