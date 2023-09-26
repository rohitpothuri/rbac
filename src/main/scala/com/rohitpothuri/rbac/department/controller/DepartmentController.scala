package com.rohitpothuri.rbac.department.controller

import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.service.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RequestMapping, ResponseStatus, RestController}


@RestController
@RequestMapping(path = Array("/department"))
class DepartmentController(@Autowired departmentService: DepartmentService) {

  @GetMapping(path = Array("/"))
  def findAll: java.util.List[Department] = departmentService.findAll

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = Array("/"))
  def create(@RequestBody  department: Department): Department =  { departmentService.save(department)}

}
