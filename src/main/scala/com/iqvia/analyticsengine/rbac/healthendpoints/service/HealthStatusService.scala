package com.iqvia.analyticsengine.rbac.healthendpoints.service

import com.iqvia.analyticsengine.rbac.healthendpoints.model.HealthStatus
import com.iqvia.analyticsengine.rbac.healthendpoints.repo.HealthStatusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HealthStatusService(@Autowired healthStatusRepository: HealthStatusRepository) {
  
  def save(healthStatus: HealthStatus) = healthStatusRepository.save(healthStatus)

  def saveAll(healthStatuses: java.util.List[HealthStatus]) = healthStatusRepository.saveAllAndFlush(healthStatuses)
  
  def findAll = healthStatusRepository.findAll()

  def findById(id: Long) = healthStatusRepository.findById(id)

}
