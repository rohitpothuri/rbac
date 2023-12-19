package com.rohitpothuri.rbac.country.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@AllArgsConstructor
public class Country {

    String name;
    String code;
}
