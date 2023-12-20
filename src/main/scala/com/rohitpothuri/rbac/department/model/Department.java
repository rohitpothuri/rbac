package com.rohitpothuri.rbac.department.model;

import com.rohitpothuri.rbac.common.model.Neo4jTimestampEntity;
import com.rohitpothuri.rbac.corporation.model.Corporation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.rohitpothuri.rbac.common.model.JPATimestampEntity;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Department")
@Setter
@Getter
public class Department extends Neo4jTimestampEntity {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

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

    /*@Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    private List<User> users;*/
}
