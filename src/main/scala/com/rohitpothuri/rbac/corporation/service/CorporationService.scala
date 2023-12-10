package com.rohitpothuri.rbac.corporation.service

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.corporation.model.Corporation
import com.rohitpothuri.rbac.corporation.repository.CorporationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util
import java.util.Optional


@Service 
class CorporationService(@Autowired corporationRepository: CorporationRepository) {
  def getAllCorporations(pageable: Pageable): Page[Corporation] = corporationRepository.findAll(pageable)

  def getCorporationById(id: Long): Optional[Corporation] = corporationRepository.findById(id)

  def saveCorporation(corporation: Corporation): Corporation = corporationRepository.save(corporation)

  def saveAll(corporations: java.util.List[Corporation]): java.util.List[Corporation] = {
    corporationRepository.saveAll(corporations)
  }

  def deleteCorporation(id: Long): Unit = {
    corporationRepository.deleteById(id)
  }

  @Transactional
  @throws[ServiceException]
  def deleteAll(): Unit = {
    try
      corporationRepository.deleteAll()
    catch {
      case e: Exception =>
        throw new ServiceException("Failed to delete corporations", e)
    }
  }
}

