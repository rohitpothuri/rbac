package com.rohitpothuri.rbac.corporation.model;

import com.rohitpothuri.rbac.department.model.Department;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Data
@Node
public class Corporation {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private boolean external;

    @Relationship(type = "DEPARTMENT_OF", direction = Relationship.Direction.INCOMING)
    private List<Department> departments;
}

