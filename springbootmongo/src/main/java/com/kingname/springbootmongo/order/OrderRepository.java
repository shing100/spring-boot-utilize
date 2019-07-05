package com.kingname.springbootmongo.order;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orderList = new ArrayList<>();

}