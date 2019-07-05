package com.kingname.springbootmongo.coffee;

import lombok.Data;

@Data
public class Coffee {

    private String id;
    private String name;
    private Integer price;

    public Coffee(String id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}