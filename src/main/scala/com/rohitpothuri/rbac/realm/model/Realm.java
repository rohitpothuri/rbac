package com.rohitpothuri.rbac.realm.model;

import lombok.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Node;
import com.rohitpothuri.rbac.common.model.Neo4jTimestampEntity;
import com.rohitpothuri.rbac.corporation.model.Corporation;
import java.util.List;

@Data
@Node("Realm")
public class Realm extends Neo4jTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Relationship(type = "CORPORATION_OF", direction = Relationship.Direction.INCOMING)
    private List<Corporation> corporations;
}
