package com.kingname.springbootsecurity;

import com.kingname.springbootsecurity.account.Account;
import com.kingname.springbootsecurity.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {


    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account kingname = accountService.createAccount("kingname", "1234");
        System.out.println(kingname.getUsername() + kingname.getPassword());
    }
}
