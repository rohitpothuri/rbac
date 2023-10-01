package com.rohitpothuri.rbac.department.model;

import jakarta.persistence.*;

import java.time.Instant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import io.swagger.v3.oas.annotations.media.Schema;
import com.rohitpothuri.rbac.common.model.TimestampEntity;

@Entity
@Table(name = "department")
public class Department extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(title = "Department ID", example = "1", required = true)
    private Long id;

    @NotNull(message = "Department name cannot be null")
    @NotEmpty(message = "Department name cannot be empty")
    @Schema(title = "Department Name", example = "Development Team", required = true)
    private String name;

    @NotNull(message = "Department description cannot be null")
    @NotEmpty(message = "Department description cannot be empty")
    @Schema(title = "Department Description", example = "Development Team", required = true)
    private String description;
    @Schema(title = "Department Rollup", example = "Development", required = true)
    @NotNull(message = "Department rollup cannot be null")
    @NotEmpty(message = "Department rollup cannot be empty")
    private String rollup;
    @NotNull(message = "Corporation Id cannot be null")
    @Schema(title = "Corporation ID", example = "1", required = true)
    private Long corporationId;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRollup() {
        return rollup;
    }

    public void setRollup(String rollup) {
        this.rollup = rollup;
    }

    public Long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

    public Department(Long id, String name, String description, String rollup, Instant createdDate, Instant updatedDate, Long corporationId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rollup = rollup;
        this.corporationId = corporationId;
    }
}
