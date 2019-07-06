package com.kingname.springbootneo4j.account;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class Role {

    @Id @GeneratedValue
    private String id;

    private String name;
}
