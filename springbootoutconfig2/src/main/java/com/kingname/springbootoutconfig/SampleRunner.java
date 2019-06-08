package com.kingname.springbootoutconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

//    @Value("${kingnmae.fullName}")
//    private String name;
//
//    @Value("${kingname.age")
//    private int age;

    @Autowired
    KingnameProperties kingnameProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===============================");
        System.out.println(kingnameProperties.getFullName());
        System.out.println(kingnameProperties.getAge());
        System.out.println(kingnameProperties.getSessionTimeout());
        System.out.println("===============================");
    }
}
