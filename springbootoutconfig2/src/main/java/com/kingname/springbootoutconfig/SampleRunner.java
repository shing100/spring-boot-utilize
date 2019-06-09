package com.kingname.springbootoutconfig;

import com.kingname.springbootoutconfig.config.BaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    private String hello;

    @Autowired
    KingnameProperties kingnameProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===============================");
        System.out.println(hello);
        System.out.println(kingnameProperties.getName());
        System.out.println(kingnameProperties.getAge());
        System.out.println("===============================");
    }
}
