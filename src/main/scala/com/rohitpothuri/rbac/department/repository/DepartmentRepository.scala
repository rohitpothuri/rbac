package com.rohitpothuri.rbac.department.repository

import com.rohitpothuri.rbac.department.model.Department
import org.springframework.data.jpa.repository.JpaRepository

trait DepartmentRepository extends JpaRepository[Department, Long] {
  def findByName(name: String): Department
}
