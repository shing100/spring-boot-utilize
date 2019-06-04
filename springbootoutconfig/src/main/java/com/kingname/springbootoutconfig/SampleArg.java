package com.kingname.springbootoutconfig;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class SampleArg {

    public SampleArg(ApplicationArguments arg) {
        System.out.println("foo " + arg.containsOption("foo"));
        // -- 로 오는  arg
        System.out.println("bar " + arg.containsOption("bar"));
    }

}
