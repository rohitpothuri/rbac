package com.rohitpothuri.rbac.department.repository

import com.rohitpothuri.rbac.department.model.Department
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

trait DepartmentRepository extends JpaRepository[Department, Long] {

  def findAllByName(departmentName: String, pageable: Pageable): java.util.List[Department]

}
