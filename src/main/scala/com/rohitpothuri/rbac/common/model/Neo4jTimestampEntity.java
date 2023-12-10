package com.rohitpothuri.rbac.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class Neo4jTimestampEntity {
    @Schema(title = "Created Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant created;

    @Schema(title = "Updated Time", example = "2023-09-30T03:29:55.410Z", required = false)
    private Instant updated;
}
