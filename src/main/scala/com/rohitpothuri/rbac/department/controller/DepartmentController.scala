package com.rohitpothuri.rbac.department.controller

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

import java.util.Optional
import scala.jdk.CollectionConverters.*


@RestController
@RequestMapping(path = Array("/departments"))
@Tag(name = "Departments", description = "Endpoints for managing departments")
class DepartmentController(@Autowired departmentService: DepartmentService) {
  @Operation(summary = "Get all departments", description = "Returns list of departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The departments were not found")))
  @GetMapping(path = Array("/"))
  def getAllDepartments(pageable: Pageable): ResponseEntity[Page[Department]] =
    ResponseEntity.ok(departmentService.findAll(pageable))


  @Operation(summary = "Get Department by ID", description = "Retrieve a department based on its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved department"),
    new ApiResponse(responseCode = "404", description = "Department not found")))
  @GetMapping(path = Array("/{id}"))
  def getDepartmentById(@PathVariable("id") id: Long): ResponseEntity[Department] = {
    val departmentOptional: Optional[Department] = departmentService.findById(id)

    if (departmentOptional.isPresent) {
      ResponseEntity.ok(departmentOptional.get())
    } else {
      ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
  }  

  private def formatViolation(violation: ConstraintViolation[_]): String = {
    s"${violation.getPropertyPath}: ${violation.getMessage}"
  }
  @Operation(summary = "Save department", description = "Returns saved department")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved department"),
    new ApiResponse(responseCode = "404", description = "Invalid department data"),
    new ApiResponse(responseCode = "500", description = "Unable to save department")))
  @PostMapping(path = Array("/"))
  def createDepartment(@Valid @RequestBody department: Department): ResponseEntity[Department] = {
    ResponseEntity.ok(departmentService.save(department))
  }

  @Operation(summary = "Save List of Departments", description = "Save a list of departments.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved departments"),
    new ApiResponse(responseCode = "500", description = "Error saving departments")))
  @PostMapping(path = Array("/save-list"), consumes = Array("application/json"))
  def saveDepartments(@RequestBody departments: java.util.List[Department]): ResponseEntity[java.util.List[Department]] = {
    new ResponseEntity[java.util.List[Department]](departmentService.saveAll(departments), HttpStatus.OK)
  }

  @DeleteMapping(path = Array("/{id}"))
  @Operation(summary = "Delete a department by ID", description = "Deletes a department based on its ID.")
  @ApiResponses(Array(
    new ApiResponse(responseCode = "200", description = "Department deleted successfully"),
    new ApiResponse(responseCode = "404", description = "Department not found"),
    new ApiResponse(responseCode = "400", description = "Invalid Department ID")
  ))
  def deleteDepartment(@PathVariable("id") id: Long): ResponseEntity[String] = {
    departmentService.deleteById(id)
    ResponseEntity.ok(s"Department $id deleted successfully")
  }

  @Operation(summary = "Delete all departments", description = "Delete all departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully deleted all departments"),
    new ApiResponse(responseCode = "500", description = "Error deleting departments")))
  @DeleteMapping("/deleteAll")
  def deleteAllDepartments(): ResponseEntity[String] = {
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
