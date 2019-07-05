package com.kingname.springbootmongo.order;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class OrderDataFetcher implements DataFetcher<Order> {

    @Override
    public Order get(DataFetchingEnvironment environment) {
        return null;
    }
}