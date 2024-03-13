package com.rohitpothuri.rbac.roles.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rohitpothuri.rbac.common.model.JPATimestampEntity;
import com.rohitpothuri.rbac.company.model.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Role extends JPATimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    @Schema(description = "Role Name", example = "AA", required = true)
    private String name;

    @NotNull(message = "Role description cannot be null")
    @NotEmpty(message = "Role description cannot be empty")
    @Schema(description = "Role Description", example = "Advanced Analytics", required = true)
    private String description;

    @Column(nullable = false, updatable = true)
    @Schema(title = "Expiration Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant expiration;

}
