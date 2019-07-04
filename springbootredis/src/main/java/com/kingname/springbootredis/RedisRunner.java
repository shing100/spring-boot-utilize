package com.kingname.springbootredis;

import com.kingname.springbootredis.account.Account;
import com.kingname.springbootredis.account.AccountRepository;
import org.omg.DynamicAny.DynValueOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();

        values.set("kingname", "kingname");
        values.set("springboot", "2.0");

        Account account = new Account();
        account.setEmail("kingname@email.com");
        account.setUsername("kingname");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getEmail());
        System.out.println(byId.get().getUsername());
    }
}
