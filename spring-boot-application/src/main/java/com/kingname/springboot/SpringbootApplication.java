package com.kingname.springboot;

import com.kingname.springbootautoconfig.Holoman;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringbootApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
        // SpringApplication.run(SpringbootApplication.class, args);
    }


    // 첫번째 빈부터 실행하여 덮어씀
//    @Bean
//    public Holoman holoman() {
//        Holoman holoman = new Holoman();
//        holoman.setName("limgeun");
//        holoman.setHowLong(60);
//        return holoman;
//    }
}
