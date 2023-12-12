package com.rohitpothuri.rbac.department.service

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.department.model.Department
import com.rohitpothuri.rbac.department.repository.DepartmentRepository
import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable, Sort}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.Optional

@Service
class DepartmentService(@Autowired departmentRepository: DepartmentRepository) {

  def findAll(pageable: Pageable): Page[Department] = departmentRepository.findAll(pageable)

  def findAllByName(departmentName: String, pageable: Pageable): java.util.List[Department] = departmentRepository.findAllByName(departmentName, pageable)
  def findById(id: String): Optional[Department] = departmentRepository.findById(id)

  def saveAll(departments: java.util.List[Department]): java.util.List[Department] = {
    departmentRepository.saveAll(departments)
  }
  @Transactional
  def save(department: Department): Department = {
    departmentRepository.save(department)
  }
  @Transactional
  def deleteById(id: String): Unit = {
    departmentRepository.deleteById(id)
  }
  @Transactional
  @throws[ServiceException]
  def deleteAll(): Unit = {
    try
      departmentRepository.deleteAll()
    catch {
      case e: Exception =>
        throw new ServiceException("Failed to delete departments", e)
    }
  }

}
