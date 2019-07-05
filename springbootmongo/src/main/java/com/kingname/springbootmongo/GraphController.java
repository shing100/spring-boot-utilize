package com.kingname.springbootmongo;

import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graphql")
public class GraphController {

    private final GraphUseCase graphUseCase;

    public GraphController(GraphUseCase graphUseCase) {
        this.graphUseCase = graphUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> grqphByQuery(@RequestBody String query) {
        ExecutionResult execute = graphUseCase.execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}

