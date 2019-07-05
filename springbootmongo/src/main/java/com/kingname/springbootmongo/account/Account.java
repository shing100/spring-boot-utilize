package com.kingname.springbootmongo.account;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Data
public class Account {

    @Id
    private String id;

    private String username;

    private String email;

}
