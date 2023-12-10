package com.rohitpothuri.rbac.corporation.model;

import com.rohitpothuri.rbac.department.model.Department;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;
import com.rohitpothuri.rbac.realm.model.Realm;

import java.util.List;

@Data
@Node
public class Corporation {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private boolean external;

    @Relationship(type = "DEPARTMENT_OF", direction = Relationship.Direction.INCOMING)
    private List<Department> departments;
}

