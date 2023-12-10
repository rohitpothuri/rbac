package com.rohitpothuri.rbac.realm.service

import com.rohitpothuri.rbac.realm.model.Realm
import com.rohitpothuri.rbac.realm.repository.RealmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service

import java.util.Optional

@Service
class RealmService(@Autowired realmRepository: RealmRepository) {
  def findAll(pageable: Pageable): Page[Realm] = realmRepository.findAll(pageable)
  def deleteAll(): Unit = realmRepository.deleteAll()
  def save(realm: Realm): Realm = realmRepository.save(realm)
  def saveAll(realms: java.util.List[Realm]): java.util.List[Realm] = {
    realmRepository.saveAll(realms)
  }
  def findById(id: Long): Optional[Realm] = realmRepository.findById(id)

  def deleteById(id: Long): Unit = {
    if (realmRepository.existsById(id)) {
      realmRepository.deleteById(id)
    } else {
      throw new Exception(s"Realm with ID $id not found.")
    }
  }


}
