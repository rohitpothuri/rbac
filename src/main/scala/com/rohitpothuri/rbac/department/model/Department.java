package com.rohitpothuri.rbac.department.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String rollup;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private Long corporationId;

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

    public String getRollup() {
        return rollup;
    }

    public void setRollup(String rollup) {
        this.rollup = rollup;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

    public Department(Long id, String name, String rollup, LocalDate createdDate, LocalDate updatedDate, Long corporationId) {
        this.id = id;
        this.name = name;
        this.rollup = rollup;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.corporationId = corporationId;
    }
}
