package com.rohitpothuri.rbac.department.repository

import com.rohitpothuri.rbac.department.model.Department
import org.springframework.data.domain.Pageable
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.repository.PagingAndSortingRepository

trait DepartmentRepository extends Neo4jRepository[Department, java.lang.String] {
  def findAllByName(departmentName: String, page: Pageable): java.util.List[Department]

}
