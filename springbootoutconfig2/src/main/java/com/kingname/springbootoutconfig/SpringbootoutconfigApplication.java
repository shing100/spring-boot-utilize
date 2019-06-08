package com.kingname.springbootoutconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableConfigurationProperties(KingnameProperties.class)
public class SpringbootoutconfigApplication {

//    @Bean
//    @ConfigurationProperties("server")
//    public ServerProperties serverProperties() {
//        return new ServerProperties();
//    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootoutconfigApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
