package com.kingname.springbootoutconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootoutconfigApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootoutconfigApplication.class);
        app.addListeners(new SampleListener());
        app.run(args);
        //SpringApplication.run(SpringbootoutconfigApplication.class, args);

    }

}
