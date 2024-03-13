package com.rohitpothuri.rbac.common.model;

import java.time.Instant;
import jakarta.persistence.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Data
public abstract class JPATimestampEntity {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(title = "Created Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant created;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    @Schema(title = "Updated Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant updated;
}
