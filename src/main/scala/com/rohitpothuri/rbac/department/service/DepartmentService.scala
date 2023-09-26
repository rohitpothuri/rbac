package com.rohitpothuri.rbac.department.service

import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.repository.DepartmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentService(@Autowired departmentRepository: DepartmentRepository) {

  def findAll: java.util.List[Department] = departmentRepository.findAll()

  def save(department: Department): Department = departmentRepository.save(department)

}
