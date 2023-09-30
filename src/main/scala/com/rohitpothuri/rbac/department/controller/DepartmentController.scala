package com.rohitpothuri.rbac.department.controller

import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.service.DepartmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponses, ApiResponse}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RequestMapping, ResponseStatus, RestController}


@RestController
@RequestMapping(path = Array("/department"))
class DepartmentController(@Autowired departmentService: DepartmentService) {
  @Operation(summary = "Get all departments", description = "Returns list of departments")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The departments were not found")))
  @GetMapping(path = Array("/"))
  def findAll: ResponseEntity[java.util.List[Department]] =
    ResponseEntity.ok(departmentService.findAll)


  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved department"),
    new ApiResponse(responseCode = "404", description = "Unable to save department")))
  @PostMapping(path = Array("/"))
  def create(@RequestBody department: Department): ResponseEntity[Department] =
    ResponseEntity.ok(departmentService.save(department))

}
