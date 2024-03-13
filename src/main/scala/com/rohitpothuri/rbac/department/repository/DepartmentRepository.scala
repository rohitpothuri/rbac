package com.rohitpothuri.rbac.department.repository

import com.rohitpothuri.rbac.department.model.Department
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.{RepositoryRestResource, RestResource}

@RepositoryRestResource(path = "department")
@Tag(name = "Department", description = "Department Entity Controller")
trait DepartmentRepository extends JpaRepository[Department, java.lang.String] with PagingAndSortingRepository[Department, java.lang.String] {
  @RestResource(path = "name")
  def findByNameContainingIgnoreCase(@Param("name") name: String, pageable: Pageable): Page[Department]
}
