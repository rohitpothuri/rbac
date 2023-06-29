package com.rohitpothuri.rbac.healthendpoints.repo

import com.rohitpothuri.rbac.healthendpoints.model.HealthStatus
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import java.sql.Date
import java.util.Optional

@Repository
trait HealthStatusRepository extends JpaRepository[HealthStatus, Integer] {
  def findByComponent(component: String): java.util.List[HealthStatus]
  def findById(id: Long): HealthStatus
  def deleteById(id: Long): HealthStatus
  def save(healthStatus: HealthStatus): HealthStatus
  def save(healthStatus: java.util.List[HealthStatus]): java.util.List[HealthStatus]
}
