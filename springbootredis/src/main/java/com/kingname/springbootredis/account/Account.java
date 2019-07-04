package com.kingname.springbootredis.account;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("account")
public class Account {

    @Id
    private String id;

    private String username;

    private String email;
}
