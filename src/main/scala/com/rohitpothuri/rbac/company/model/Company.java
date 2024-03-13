package com.rohitpothuri.rbac.company.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rohitpothuri.rbac.common.model.JPATimestampEntity;
import com.rohitpothuri.rbac.department.model.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Company extends JPATimestampEntity {

    @Id
    @Schema(description = "Customer Portal Company ID", example = "1", required = true)
    private String id;

    @Schema(description = "Customer Portal Company Name", example = "IQVIA", required = true)
    @NotNull(message = "Customer Portal Company Name cannot be null")
    @NotEmpty(message = "Customer Portal Company Name be empty")
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("company")
    private List<Department> departments;
}
