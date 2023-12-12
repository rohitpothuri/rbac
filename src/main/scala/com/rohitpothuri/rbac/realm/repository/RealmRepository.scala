package com.rohitpothuri.rbac.realm.repository

import com.rohitpothuri.rbac.realm.model.Realm
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.lang.Long

@Repository
trait RealmRepository extends Neo4jRepository[Realm, String] {
  def findByName(name: Long): Realm
}