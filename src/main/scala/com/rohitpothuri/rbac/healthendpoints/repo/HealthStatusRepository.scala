package com.rohitpothuri.rbac.healthendpoints.repo

import com.rohitpothuri.rbac.healthendpoints.model.HealthStatus
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import java.sql.Date
import java.util.Optional

@Repository
trait HealthStatusRepository extends JpaRepository[HealthStatus, Long] with PagingAndSortingRepository[HealthStatus, Long] {

}
