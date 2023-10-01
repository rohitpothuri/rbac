package com.rohitpothuri.rbac.department.controller

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.service.DepartmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import jakarta.validation.{ConstraintViolation, ConstraintViolationException, Valid, ValidationException}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, HttpStatusCode, ResponseEntity}
import org.springframework.web.bind.annotation.*

import scala.jdk.CollectionConverters.*


@RestController
@RequestMapping(path = Array("/department"))
class DepartmentController(@Autowired departmentService: DepartmentService) {
  @Operation(summary = "Get all departments", description = "Returns list of departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The departments were not found")))
  @GetMapping(path = Array("/"))
  def findAll(pageable: Pageable): ResponseEntity[Page[Department]] =
    ResponseEntity.ok(departmentService.findAll(pageable))

  private def formatViolation(violation: ConstraintViolation[_]): String = {
    s"${violation.getPropertyPath}: ${violation.getMessage}"
  }
  @Operation(summary = "Save department", description = "Returns saved department")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved department"),
    new ApiResponse(responseCode = "404", description = "Department not found"),
    new ApiResponse(responseCode = "404", description = "Unable to save department")))
  @PostMapping(path = Array("/"))
  def create(@Valid @RequestBody department: Department): ResponseEntity[Department] = {
    ResponseEntity.ok(departmentService.save(department))
  }

  @DeleteMapping(path = Array("/{id}"))
  @Operation(summary = "Delete a department by ID", description = "Deletes a department based on its ID.")
  @ApiResponses(Array(
    new ApiResponse(responseCode = "200", description = "Department deleted successfully"),
    new ApiResponse(responseCode = "404", description = "Department not found"),
    new ApiResponse(responseCode = "400", description = "Invalid department data")
  ))
  def delete(@PathVariable("id") id: Long): ResponseEntity[String] = {
    departmentService.deleteById(id)
    ResponseEntity.ok(s"Department $id deleted successfully")
  }

  @Operation(summary = "Delete all departments", description = "Delete all departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully deleted all departments"),
    new ApiResponse(responseCode = "404", description = "The department was not found")))
  @DeleteMapping("/deleteAll")
  def deleteAllDepartments: ResponseEntity[String] = {
    try {
      departmentService.deleteAll()
      ResponseEntity.ok("All departments deleted successfully")
    } catch {
      case e: ServiceException =>
        // Log the error for debugging
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting departments: " + e.getMessage)
    }
  }

}
