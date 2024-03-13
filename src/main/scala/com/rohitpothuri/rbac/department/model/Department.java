package com.rohitpothuri.rbac.department.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rohitpothuri.rbac.common.model.JPATimestampEntity;
import com.rohitpothuri.rbac.company.model.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Department extends JPATimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull(message = "Department name cannot be null")
    @NotEmpty(message = "Department name cannot be empty")
    @Schema(description = "Department Name", example = "Development Team", required = true)
    private String name;

    @NotNull(message = "Department description cannot be null")
    @NotEmpty(message = "Department description cannot be empty")
    @Schema(description = "Department Description", example = "Development Team", required = true)
    private String description;

    @Schema(description = "Department Rollup", example = "Development", required = true)
    @NotNull(message = "Department rollup cannot be null")
    @NotEmpty(message = "Department rollup cannot be empty")
    private String rollup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("departments")
    @JoinColumn(name = "company_id")
    private Company company;

}
