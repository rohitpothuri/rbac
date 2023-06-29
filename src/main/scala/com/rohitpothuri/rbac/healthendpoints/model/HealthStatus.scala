package com.rohitpothuri.rbac.healthendpoints.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import io.swagger.v3.oas.annotations


@SerialVersionUID(1L)
@JsonInclude(Include.NON_EMPTY)
@Entity
class HealthStatus extends Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private val id = 0L
  private var status: String = null
  private var component: String = null

  def this(status: String, component: String) = {
    this()
    this.status = status
    this.component = component
  }

  override def toString = s"HealthStatus($id, $status, $component)"
}
