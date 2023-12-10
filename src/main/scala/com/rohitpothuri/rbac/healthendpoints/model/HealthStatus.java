package com.rohitpothuri.rbac.healthendpoints.model;

import com.rohitpothuri.rbac.common.model.JPATimestampEntity;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@JsonInclude(Include.NON_EMPTY)
@Entity
@Data
public class HealthStatus extends JPATimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String component;
}
