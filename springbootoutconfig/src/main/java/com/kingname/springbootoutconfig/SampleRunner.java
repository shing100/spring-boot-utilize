package com.kingname.springbootoutconfig;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@Order(1)
public class SampleRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("foo " + args.containsOption("foo"));
        // -- 로 오는  arg
        System.out.println("bar " + args.containsOption("bar"));
    }
}
