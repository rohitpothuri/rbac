package com.rohitpothuri.rbac.realm.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import com.rohitpothuri.rbac.common.model.Neo4jTimestampEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Realm")
@Setter
@Getter
public class Realm extends Neo4jTimestampEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
