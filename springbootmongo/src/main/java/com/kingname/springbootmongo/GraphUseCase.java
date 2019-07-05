package com.kingname.springbootmongo;

import graphql.ExecutionResult;

public interface GraphUseCase {
    ExecutionResult execute(String query);
}
