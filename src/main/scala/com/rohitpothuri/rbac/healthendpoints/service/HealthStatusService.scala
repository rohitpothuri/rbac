package com.rohitpothuri.rbac.healthendpoints.service

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.healthendpoints.model.HealthStatus
import com.rohitpothuri.rbac.healthendpoints.repo.HealthStatusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service

import java.util
import java.util.Optional

@Service
class HealthStatusService(@Autowired healthStatusRepository: HealthStatusRepository) {
  
  def save(healthStatus: HealthStatus): HealthStatus = healthStatusRepository.save(healthStatus)

  def saveAll(healthStatuses: java.util.List[HealthStatus]): util.List[HealthStatus] = healthStatusRepository.saveAllAndFlush(healthStatuses)
  
  def findAll(pageable: Pageable): Page[HealthStatus] = healthStatusRepository.findAll(pageable)

  def findById(id: Long): Optional[HealthStatus] = healthStatusRepository.findById(id)

  def deleteById(id: Long): Unit = {
    // Check if the health status exists
    if (healthStatusRepository.existsById(id)) {
      // Delete the health status by ID
      healthStatusRepository.deleteById(id)
    } else {
      // Throw a ServiceException if the health status does not exist
      throw new ServiceException(s"Health status with ID $id not found.", new Exception("Health status with ID $id not found."))
    }
  }
  def deleteAll(): Unit = {
    // Delete all health statuses
    healthStatusRepository.deleteAll()
  }

}
