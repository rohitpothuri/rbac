package com.rohitpothuri.rbac.common.model;

import java.time.Instant;

import jakarta.persistence.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class TimestampEntity {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(title = "Created Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant created;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    @Schema(title = "Updated Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant updated;


    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public TimestampEntity() {
    }

    public TimestampEntity(Instant created, Instant updated) {
        this.created = created;
        this.updated = updated;
    }
}
