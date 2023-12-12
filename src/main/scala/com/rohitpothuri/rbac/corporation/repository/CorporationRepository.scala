package com.rohitpothuri.rbac.corporation.repository

import com.rohitpothuri.rbac.corporation.model.Corporation
import org.springframework.data.neo4j.repository.Neo4jRepository


trait CorporationRepository extends Neo4jRepository[Corporation, String] {
  
}

