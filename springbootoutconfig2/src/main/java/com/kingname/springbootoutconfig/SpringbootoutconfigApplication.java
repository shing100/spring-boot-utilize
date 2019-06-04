package com.kingname.springbootoutconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootoutconfigApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootoutconfigApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
