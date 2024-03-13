package com.rohitpothuri.rbac.company.repository

import com.rohitpothuri.rbac.company.model.Company
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "company")
@Tag(name = "Company", description = "Company Entity Controller")
trait CompanyRepository extends JpaRepository[Company, java.lang.String] with PagingAndSortingRepository[Company, java.lang.String] {

}
