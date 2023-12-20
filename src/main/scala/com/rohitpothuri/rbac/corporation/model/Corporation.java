package com.rohitpothuri.rbac.corporation.model;

import com.rohitpothuri.rbac.department.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
public class Corporation {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    private boolean external;

    @Relationship(type = "DEPARTMENT_OF", direction = Relationship.Direction.INCOMING)
    private List<Department> departments;
}

